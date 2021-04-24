package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.service.model.NewsRequestDto
import java.util.*

interface NewsApi {

    suspend fun getNews(
        query: String,
        page: Int?,
        timeoutInMillis: Long = 30000,
        fromDate: Date = Date()
    ): Either<RestResult, NewsRequestDto>
}
