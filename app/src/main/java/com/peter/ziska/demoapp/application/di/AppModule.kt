package com.peter.ziska.demoapp.application.di

import android.app.Application
import android.content.Context
import com.peter.ziska.demoapp.flows.view.sample.di.SampleModule
import dagger.Binds
import dagger.Module

@Module(includes = [SampleModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
