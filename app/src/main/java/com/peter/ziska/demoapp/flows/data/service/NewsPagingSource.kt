package com.peter.ziska.demoapp.flows.data.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.ziska.demoapp.base.either.onLeft
import com.peter.ziska.demoapp.flows.data.mapper.toArticle
import com.peter.ziska.demoapp.flows.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val query: String,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } ?: DEFAULT_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val nextPageNumber = params.key ?: DEFAULT_PAGE
        val response = newsApi.getNews(query, nextPageNumber)

        val successResponse = response.onLeft {
            return LoadResult.Error(IllegalArgumentException(it.l.toString()))
        }
        return LoadResult.Page(
            data = successResponse.map { it.toArticle() },
            prevKey = null,
            nextKey = nextPageNumber.plus(1)
        )
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}