package com.peter.ziska.demoapp.flows.view.detail.di

import com.peter.ziska.demoapp.flows.view.detail.view.NewsDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [NewsDetailModule.AbstractModule::class])
class NewsDetailModule {

    @Module
    interface AbstractModule {
        @ContributesAndroidInjector
        fun contributeFragment(): NewsDetailFragment
    }
}
