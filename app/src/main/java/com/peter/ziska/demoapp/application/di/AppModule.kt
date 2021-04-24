package com.peter.ziska.demoapp.application.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DateModule::class,
        FragmentModules::class,
        RepositoryModule::class,
        StorageModule::class,
    ]
)
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
