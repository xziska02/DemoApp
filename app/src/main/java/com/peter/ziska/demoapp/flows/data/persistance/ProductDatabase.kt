package com.peter.ziska.demoapp.flows.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peter.ziska.demoapp.flows.data.persistance.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}
