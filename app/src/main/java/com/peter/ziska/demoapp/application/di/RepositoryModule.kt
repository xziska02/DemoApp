package com.peter.ziska.demoapp.application.di

import com.peter.ziska.demoapp.flows.data.products.ProductRepositoryImpl
import com.peter.ziska.demoapp.flows.domain.products.ProductRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository
}