package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.model.ArticleDto
import java.util.*

interface NewsApi {

    suspend fun getNews(
        query: String,
        page: Int,
        timeoutInMillis: Long = 3000,
        fromDate: Date = Date()
    ): Either<RestResult, List<ArticleDto>>
}
