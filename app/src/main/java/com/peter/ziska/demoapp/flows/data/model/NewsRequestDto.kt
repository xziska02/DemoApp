package com.peter.ziska.demoapp.flows.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsRequestDto(
	val status: String,
	val total_hits: Int,
	val page: Int,
	val total_pages: Int,
	val page_size: Int,
	val articles: List<ArticleDto>,
	val user_input: UserInput
)
