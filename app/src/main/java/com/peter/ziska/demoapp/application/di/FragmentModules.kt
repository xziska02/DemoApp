package com.peter.ziska.demoapp.application.di

import com.peter.ziska.demoapp.flows.view.detail.di.NewsDetailModule
import com.peter.ziska.demoapp.flows.view.news.di.NewsModule
import dagger.Module

@Module(
    includes = [
        NewsModule::class,
        NewsDetailModule::class,
    ]
)
abstract class FragmentModules