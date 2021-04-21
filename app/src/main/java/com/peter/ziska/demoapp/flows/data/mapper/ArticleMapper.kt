package com.peter.ziska.demoapp.flows.data.mapper

import com.peter.ziska.demoapp.flows.data.model.ArticleDto
import com.peter.ziska.demoapp.flows.domain.model.Article


fun ArticleDto.toArticle() = Article(
    author, title, description, url, urlToImage, publishedAt, content
)