package com.peter.ziska.demoapp.flows.view.products.presenter

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.peter.ziska.demoapp.base.either.withLeft
import com.peter.ziska.demoapp.base.viewmodel.BaseViewModel
import com.peter.ziska.demoapp.extension.launchOnIO
import com.peter.ziska.demoapp.flows.data.service.RestError
import com.peter.ziska.demoapp.flows.domain.model.Product
import com.peter.ziska.demoapp.flows.domain.products.GetProducts
import com.peter.ziska.demoapp.flows.domain.products.IsDatabaseEmpty
import com.peter.ziska.demoapp.flows.domain.products.PreFetchProducts
import com.peter.ziska.demoapp.flows.domain.products.UpdateProduct
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import timber.log.Timber
import javax.inject.Inject

@FlowPreview
class ProductsViewModel @Inject constructor(
    getProductsUseCase: GetProducts,
    private val updateProductUseCase: UpdateProduct,
    private val preFetchProducts: PreFetchProducts,
    private val isDatabaseEmpty: IsDatabaseEmpty,
) : BaseViewModel() {

    val productFlow = getProductsUseCase().cachedIn(viewModelScope)

    private val errorSender = BroadcastChannel<RestError>(Channel.BUFFERED)
    val errorReceiver
        get() = errorSender.asFlow().conflate()

    init {
        launchOnIO {
            isDatabaseEmpty().takeIf { isEmpty -> isEmpty }?.let { fetch() }
        }
    }

    fun fetch() = launchOnIO {
        preFetchProducts().withLeft {
            Timber.e("--- Error data failed: $it")
            errorSender.send(it)
        }
    }

    fun updateProduct(product: Product) = launchOnIO {
        updateProductUseCase(product)
    }
}