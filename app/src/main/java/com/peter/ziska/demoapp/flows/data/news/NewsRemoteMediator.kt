package com.peter.ziska.demoapp.flows.data.news

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.peter.ziska.demoapp.base.either.onLeft
import com.peter.ziska.demoapp.flows.data.persistance.NewsDatabase
import com.peter.ziska.demoapp.flows.data.persistance.model.ArticleEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.RemoteKeys
import com.peter.ziska.demoapp.flows.data.service.NewsApi
import com.peter.ziska.demoapp.flows.data.service.mapper.toArticleEntity
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val query: String,
    private val database: NewsDatabase,
    private val newsApi: NewsApi,
) : RemoteMediator<Int, ArticleEntity>() {
    private val newsDao = database.newsDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> DEFAULT_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(true)
            }
        }
        delay(1000) //handling API limitation for 1 request per second

        val response = newsApi.getNews(query, loadKey).onLeft {
            return MediatorResult.Error(IllegalArgumentException("DATA: ${it.l}"))
        }

        val endOfPagination = loadKey == response.total_pages

        database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.clear()
                newsDao.clear()
            }
            val prevKey = if (loadKey == DEFAULT_PAGE) null else loadKey - 1
            val nextKey = if (endOfPagination) null else loadKey + 1
            val keys = response.articles.map {
                RemoteKeys(it.id, nextKey, prevKey)
            }
            remoteKeyDao.insertOrReplace(keys)
            newsDao.insert(response.articles.map { it.toArticleEntity() })
        }

        return MediatorResult.Success(endOfPaginationReached = endOfPagination)
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ArticleEntity>): RemoteKeys? =
        state.lastItemOrNull()?.let { news ->
            database.withTransaction { database.remoteKeyDao().getRemoteKey(news.remoteId) }
        }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

