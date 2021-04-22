package com.peter.ziska.demoapp.application.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.ziska.demoapp.base.viewmodel.ViewModelFactory
import com.peter.ziska.demoapp.base.viewmodel.ViewModelKey
import com.peter.ziska.demoapp.flows.view.detail.di.NewsDetailViewModelModule
import com.peter.ziska.demoapp.flows.view.main.presenter.MainViewModel
import com.peter.ziska.demoapp.flows.view.news.di.NewsViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NewsViewModelModule::class, NewsDetailViewModelModule::class])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}
