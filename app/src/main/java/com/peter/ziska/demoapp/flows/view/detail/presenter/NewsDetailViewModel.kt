package com.peter.ziska.demoapp.flows.view.detail.presenter

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peter.ziska.demoapp.base.viewmodel.BaseViewModel
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.view.detail.view.NewsDetailViewState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor() : BaseViewModel() {

    private val _viewState: MutableLiveData<NewsDetailViewState> = MutableLiveData()
    val viewState: LiveData<NewsDetailViewState>
        get() = _viewState

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)

        val json = requireNotNull(arguments?.getString(KEY_ARTICLE))

        val article = Json.decodeFromString<Article>(json)

        _viewState.value = NewsDetailViewState.NewsDetailArticle(article)
    }

    companion object {
        private const val KEY_ARTICLE = "article"
    }
}
