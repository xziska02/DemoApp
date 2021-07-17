package com.peter.ziska.demoapp.flows.view.products.di

import com.peter.ziska.demoapp.flows.view.products.navigation.ProductsNavigator
import com.peter.ziska.demoapp.flows.view.products.view.ProductsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [ProductsModule.AbstractModule::class])
class ProductsModule {

    @Module
    interface AbstractModule {
        @ContributesAndroidInjector
        fun contributeFragment(): ProductsFragment
    }

    @Singleton
    @Provides
    fun navigator() = ProductsNavigator()
}
