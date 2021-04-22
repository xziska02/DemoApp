package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.model.ArticleDto
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class NewsApiImpl @Inject constructor(
    private val retrofit: Retrofit,
) : NewsApi, ApiHelper() {

    private val service by lazy { retrofit.create(NewsService::class.java) }

    override suspend fun getNews(
        query: String,
        page: Int,
        timeoutInMillis: Long,
        fromDate: Date
    ): Either<RestResult, List<ArticleDto>> = tryRequest {
        withTimeout(timeoutInMillis) {
            suspendCancellableCoroutine { continuation ->
                service.getNews(query, page)
                    .enqueue(RetrofitCallbacks(continuation) {
                        Either.Right(it.articles)
                    })
            }
        }
    }
}
