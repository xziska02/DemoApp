package com.peter.ziska.demoapp.flows.domain.news

import androidx.paging.PagingData
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.domain.model.BaseArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(query: String): Flow<PagingData<BaseArticle>>
}