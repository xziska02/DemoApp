package com.peter.ziska.demoapp.flows.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsRequestDto (
	val status : String,
	val totalResults : Int,
	val articles : List<ArticleDto>
)