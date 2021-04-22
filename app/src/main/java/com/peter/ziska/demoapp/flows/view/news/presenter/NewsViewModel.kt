package com.peter.ziska.demoapp.flows.view.news.presenter

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.domain.news.FetchNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNews
) : ViewModel() {

    suspend fun fetchNews(query: String? = DEFAULT_SEARCH): Flow<PagingData<Article>>? =
        fetchNewsUseCase(query ?: DEFAULT_SEARCH)

    companion object {
        private const val DEFAULT_SEARCH = "android"
    }
}