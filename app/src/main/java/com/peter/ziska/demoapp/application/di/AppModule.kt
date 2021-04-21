package com.peter.ziska.demoapp.application.di

import android.app.Application
import android.content.Context
import com.peter.ziska.demoapp.flows.view.news.di.NewsModule
import dagger.Binds
import dagger.Module

@Module(includes = [NewsModule::class, NetworkModule::class, DateModule::class, RepositoryModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
