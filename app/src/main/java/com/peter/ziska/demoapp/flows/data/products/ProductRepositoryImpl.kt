package com.peter.ziska.demoapp.flows.data.products

import androidx.paging.*
import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.base.either.flatMap
import com.peter.ziska.demoapp.base.either.withLeft
import com.peter.ziska.demoapp.base.either.withRight
import com.peter.ziska.demoapp.flows.data.persistance.ProductDao
import com.peter.ziska.demoapp.flows.data.persistance.ProductDatabase
import com.peter.ziska.demoapp.flows.data.persistance.mapper.toProduct
import com.peter.ziska.demoapp.flows.data.persistance.mapper.toProductEntity
import com.peter.ziska.demoapp.flows.data.service.NotinoApi
import com.peter.ziska.demoapp.flows.data.service.RestError
import com.peter.ziska.demoapp.flows.data.service.mapper.toProductEntity
import com.peter.ziska.demoapp.flows.domain.model.Product
import com.peter.ziska.demoapp.flows.domain.products.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val database: ProductDatabase,
    private val productDao: ProductDao,
    private val notinoApi: NotinoApi,
) : ProductRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getProducts(): Flow<PagingData<Product>> = Pager(
        config = PagingConfig(4),
        pagingSourceFactory = { database.productDao().getPagingSourceProducts() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toProduct()
        }
    }

    override suspend fun preFetchProducts(): Either<RestError, Unit> =
        notinoApi.getProducts().withRight {
            Timber.e("--- Data: $it")
            productDao.clear()
            Timber.e("--- data: ${productDao.getProducts().count()}")
            productDao.insert(it.products.map { it.toProductEntity() })
            Timber.e("--- data: ${productDao.getProducts().count()}")
        }.withLeft {
            productDao.clear()
        }.flatMap {
            Either.Right(Unit)
        }

    override fun storeProducts(products: List<Product>) {
        productDao.insert(products.map { it.toProductEntity() })
    }

    override fun clear() {
        productDao.clear()
    }

    override fun updateProduct(product: Product) {
        productDao.updateProduct(product.toProductEntity())
    }

    override fun isDatabaseEmtpy(): Boolean = productDao.getProducts().isEmpty()
}