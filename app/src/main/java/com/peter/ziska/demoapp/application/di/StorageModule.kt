package com.peter.ziska.demoapp.application.di

import android.content.Context
import androidx.room.Room
import com.peter.ziska.demoapp.flows.data.persistance.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news_db").build()

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase) = newsDatabase.newsDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(newsDatabase: NewsDatabase) = newsDatabase.remoteKeyDao()

}