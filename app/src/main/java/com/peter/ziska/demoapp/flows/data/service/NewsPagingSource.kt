package com.peter.ziska.demoapp.flows.data.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.base.either.onLeft
import com.peter.ziska.demoapp.flows.data.model.ArticleDto

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val query: String,
    private val onLeft: (Either.Left<RestResult>) -> Unit
) : PagingSource<Int, ArticleDto>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } ?: DEFAULT_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val nextPageNumber = params.key ?: DEFAULT_PAGE
        val response = newsApi.getNews(query, nextPageNumber)

        val successResponse = response.onLeft {
            onLeft(it)
            return LoadResult.Error(IllegalArgumentException(it.l.toString()))
        }
        return LoadResult.Page(
            data = successResponse,
            prevKey = null,
            nextKey = nextPageNumber.plus(1)
        )
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}