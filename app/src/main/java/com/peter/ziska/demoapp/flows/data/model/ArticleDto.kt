package com.peter.ziska.demoapp.flows.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("_id")
    val id: String,
    val author: String? ,
    val title: String,
    val media: String,
    @SerialName("published_date")
    val publishedDate: String,
    val summary: String,
    val published_date_precision : String?,
    val link : String?,
    val clean_url : String?,
    val rights : String?,
    val rank : Int?,
    val topic : String?,
    val country : String?,
    val language : String?,
    val authors : List<String>?,
    val is_opinion : Boolean?,
    val twitter_account : String?,
    @SerialName("_score")
    val score : Double?,
)