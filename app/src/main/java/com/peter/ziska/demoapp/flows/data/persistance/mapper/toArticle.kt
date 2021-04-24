package com.peter.ziska.demoapp.flows.data.persistance.mapper

import com.peter.ziska.demoapp.flows.data.persistance.model.ArticleEntity
import com.peter.ziska.demoapp.flows.domain.model.Article

fun ArticleEntity.toArticle() = Article(
    id.toString(), author, title, media, publishedDate, summary
)
