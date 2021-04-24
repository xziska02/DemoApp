package com.peter.ziska.demoapp.flows.data.persistance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val articleKey: String,
    val nextKey: Int?,
    val prevKey: Int?,
)