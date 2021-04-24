package com.peter.ziska.demoapp.flows.view.news.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.peter.ziska.demoapp.base.viewmodel.BaseViewModel
import com.peter.ziska.demoapp.flows.domain.news.FetchNews
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNews,
) : BaseViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    val articles = currentQuery.switchMap { queryString ->
        fetchNewsUseCase(queryString).cachedIn(viewModelScope).asLiveData()
    }

    fun fetch(query: String? = null) {
        currentQuery.value = query ?: currentQuery.value
    }

    companion object {
        private const val DEFAULT_QUERY = "android"
    }
}