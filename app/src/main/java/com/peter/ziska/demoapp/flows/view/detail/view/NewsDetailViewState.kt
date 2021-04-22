package com.peter.ziska.demoapp.flows.view.detail.view

import com.peter.ziska.demoapp.flows.domain.model.Article


sealed class NewsDetailViewState {
    class NewsDetailArticle(val article: Article): NewsDetailViewState()
}