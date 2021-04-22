package com.peter.ziska.demoapp.application.di

import android.app.Application
import android.content.Context
import com.peter.ziska.demoapp.flows.view.detail.di.NewsDetailModule
import com.peter.ziska.demoapp.flows.view.news.di.NewsModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DateModule::class,
        FragmentModules::class,
        RepositoryModule::class,
    ]
)
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
