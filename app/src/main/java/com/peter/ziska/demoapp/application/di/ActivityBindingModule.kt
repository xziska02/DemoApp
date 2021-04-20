package com.peter.ziska.demoapp.application.di

import com.peter.ziska.demoapp.flows.view.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}