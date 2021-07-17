package com.peter.ziska.demoapp.application.di

import android.content.Context
import androidx.room.Room
import com.peter.ziska.demoapp.flows.data.persistance.ProductDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): ProductDatabase =
        Room.databaseBuilder(context, ProductDatabase::class.java, "products_db").build()

    @Singleton
    @Provides
    fun provideProductsDao(productDatabase: ProductDatabase) = productDatabase.productDao()
}