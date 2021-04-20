package com.peter.ziska.demoapp.flows.view.sample.di

import androidx.lifecycle.ViewModel
import com.peter.ziska.demoapp.base.viewmodel.ViewModelKey
import com.peter.ziska.demoapp.flows.view.sample.presenter.SampleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SampleViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    abstract fun bindsViewModel(viewModel: SampleViewModel): ViewModel
}
