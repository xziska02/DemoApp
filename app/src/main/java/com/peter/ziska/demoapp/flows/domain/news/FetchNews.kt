package com.peter.ziska.demoapp.flows.domain.news

import androidx.paging.PagingData
import com.peter.ziska.demoapp.base.usecase.BaseParameterUseCase
import com.peter.ziska.demoapp.flows.domain.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchNews @Inject constructor(
    private val newsRepository: NewsRepository,
) : BaseParameterUseCase<String, Flow<PagingData<Article>>>() {

    override suspend fun run(input: String): Flow<PagingData<Article>> =
        newsRepository.getNews(input)
}