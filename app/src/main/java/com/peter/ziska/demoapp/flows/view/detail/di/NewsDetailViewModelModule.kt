package com.peter.ziska.demoapp.flows.view.detail.di

import androidx.lifecycle.ViewModel
import com.peter.ziska.demoapp.base.viewmodel.ViewModelKey
import com.peter.ziska.demoapp.flows.view.detail.presenter.NewsDetailViewModel
import com.peter.ziska.demoapp.flows.view.news.presenter.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    abstract fun bindsViewModel(viewModel: NewsDetailViewModel): ViewModel
}
