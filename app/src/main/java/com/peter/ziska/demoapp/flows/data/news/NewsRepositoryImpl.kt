package com.peter.ziska.demoapp.flows.data.news

import androidx.paging.*
import com.peter.ziska.demoapp.flows.data.persistance.NewsDatabase
import com.peter.ziska.demoapp.flows.data.persistance.mapper.toArticle
import com.peter.ziska.demoapp.flows.data.service.NewsApi
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.domain.model.BaseArticle
import com.peter.ziska.demoapp.flows.domain.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val database: NewsDatabase,
) : NewsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getNews(query: String): Flow<PagingData<BaseArticle>> = Pager(
        config = PagingConfig(10),
        remoteMediator = NewsRemoteMediator(query, database, newsApi),
        pagingSourceFactory = { database.newsDao().getArticleByQuery() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toArticle()
        }
    }
}