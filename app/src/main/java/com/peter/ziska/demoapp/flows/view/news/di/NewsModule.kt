package com.peter.ziska.demoapp.flows.view.news.di

import com.peter.ziska.demoapp.flows.view.news.navigation.NewsNavigator
import com.peter.ziska.demoapp.flows.view.news.view.NewsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [NewsModule.AbstractModule::class])
class NewsModule {

    @Module
    interface AbstractModule {
        @ContributesAndroidInjector
        fun contributeFragment(): NewsFragment
    }

    @Singleton
    @Provides
    fun navigator() = NewsNavigator()
}
