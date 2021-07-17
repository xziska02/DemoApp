package com.peter.ziska.demoapp.application.di

import com.peter.ziska.demoapp.flows.view.products.di.ProductsModule
import dagger.Module

@Module(
    includes = [
        ProductsModule::class,
    ]
)
abstract class FragmentModules