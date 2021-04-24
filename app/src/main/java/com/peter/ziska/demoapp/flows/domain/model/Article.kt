package com.peter.ziska.demoapp.flows.domain.model

import com.peter.ziska.demoapp.flows.domain.model.BaseArticle.ArticleType.Companion.BASIC_ARTICLE
import kotlinx.serialization.Serializable

@Serializable
class Article(
    override val id: String,
    val author: String,
    val title: String,
    val urlToImage: String,
    val publishedDate: String,
    val content: String
) : BaseArticle(BASIC_ARTICLE)
