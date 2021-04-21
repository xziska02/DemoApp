package com.peter.ziska.demoapp.flows.data.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peter.ziska.demoapp.flows.data.service.NewsApi
import com.peter.ziska.demoapp.flows.data.service.NewsPagingSource
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.domain.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {

    override suspend fun getNews(query: String): Flow<PagingData<Article>> =
        Pager(config = PagingConfig(10, enablePlaceholders = true), pagingSourceFactory = {
            NewsPagingSource(newsApi, query)
        }).flow

}