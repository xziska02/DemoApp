package com.peter.ziska.demoapp.flows.domain.products

import androidx.paging.PagingData
import com.peter.ziska.demoapp.base.usecase.BaseWithoutParameterUseCase
import com.peter.ziska.demoapp.flows.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetProducts @Inject constructor(
    private val productRepository: ProductRepository,
) : BaseWithoutParameterUseCase<Flow<PagingData<Product>>>() {

    override fun run(): Flow<PagingData<Product>> = productRepository.getProducts()
}