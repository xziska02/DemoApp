package com.peter.ziska.demoapp.application.di

import com.peter.ziska.demoapp.flows.data.news.NewsRepositoryImpl
import com.peter.ziska.demoapp.flows.domain.news.NewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}