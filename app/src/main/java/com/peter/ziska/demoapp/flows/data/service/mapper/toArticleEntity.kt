package com.peter.ziska.demoapp.flows.data.service.mapper

import com.peter.ziska.demoapp.flows.data.service.model.ArticleDto
import com.peter.ziska.demoapp.flows.data.persistance.model.ArticleEntity

fun ArticleDto.toArticleEntity() = ArticleEntity(
    id, author ?: "Unknown", title, media, publishedDate, summary
)