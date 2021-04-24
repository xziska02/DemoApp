package com.peter.ziska.demoapp.flows.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val author: String,
    val title: String,
    val urlToImage: String,
    val publishedDate: String,
    val content: String
)