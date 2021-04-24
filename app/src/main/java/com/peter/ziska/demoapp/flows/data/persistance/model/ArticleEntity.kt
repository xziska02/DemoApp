package com.peter.ziska.demoapp.flows.data.persistance.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "article")
data class ArticleEntity(
    val remoteId: String,
    val author: String,
    val title: String,
    val media: String,
    val publishedDate: String,
    val summary: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
