package com.peter.ziska.demoapp.flows.view.detail.view

import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.flows.view.detail.presenter.NewsDetailViewModel


class NewsDetailFragment : BaseFragment<NewsDetailViewModel>(R.layout.fragment_news_detail) {

    override val viewModelClass: Class<NewsDetailViewModel>
        get() = NewsDetailViewModel::class.java

    override fun onInitializeView() {
        super.onInitializeView()

        setHasOptionsMenu(false)
    }
}