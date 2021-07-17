package com.peter.ziska.demoapp.flows.view.products.di

import androidx.lifecycle.ViewModel
import com.peter.ziska.demoapp.base.viewmodel.ViewModelKey
import com.peter.ziska.demoapp.flows.view.products.presenter.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    abstract fun bindsViewModel(viewModel: ProductsViewModel): ViewModel
}
