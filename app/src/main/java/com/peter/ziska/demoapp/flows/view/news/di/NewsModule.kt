package com.peter.ziska.demoapp.flows.view.news.di

import com.peter.ziska.demoapp.flows.view.news.view.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [NewsModule.AbstractModule::class, NewsViewModelModule::class])
class NewsModule {

    @Module
    interface AbstractModule {
        @ContributesAndroidInjector
        fun contributeFragment(): NewsFragment
    }
}
