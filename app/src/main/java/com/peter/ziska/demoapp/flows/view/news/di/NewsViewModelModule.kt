package com.peter.ziska.demoapp.flows.view.news.di

import androidx.lifecycle.ViewModel
import com.peter.ziska.demoapp.base.viewmodel.ViewModelKey
import com.peter.ziska.demoapp.flows.view.news.presenter.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindsViewModel(viewModel: NewsViewModel): ViewModel
}
