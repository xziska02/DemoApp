package com.peter.ziska.demoapp.flows.domain.products

import androidx.paging.PagingData
import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.service.RestError
import com.peter.ziska.demoapp.flows.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<PagingData<Product>>

    suspend fun preFetchProducts(): Either<RestError, Unit>

    fun storeProducts(products: List<Product>)

    fun clear()

    fun updateProduct(product: Product)
    fun isDatabaseEmtpy(): Boolean
}