package com.peter.ziska.demoapp.flows.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peter.ziska.demoapp.flows.data.persistance.model.ArticleEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.RemoteKeys

@Database(entities = [ArticleEntity::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
