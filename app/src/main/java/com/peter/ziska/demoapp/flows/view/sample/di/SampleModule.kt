package com.peter.ziska.demoapp.flows.view.sample.di

import com.peter.ziska.demoapp.flows.view.sample.view.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [SampleModule.AbstractModule::class, SampleViewModelModule::class])
class SampleModule {

    @Module
    interface AbstractModule {
        @ContributesAndroidInjector
        fun contributeFragment(): SampleFragment
    }
}
