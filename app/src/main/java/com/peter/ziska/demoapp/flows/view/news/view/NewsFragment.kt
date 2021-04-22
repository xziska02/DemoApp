package com.peter.ziska.demoapp.flows.view.news.view

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.flows.view.news.adapter.NewsPageAdapter
import com.peter.ziska.demoapp.flows.view.news.presenter.NewsViewModel
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsFragment : BaseFragment<NewsViewModel>(R.layout.news_fragment) {

    override val viewModelClass: Class<NewsViewModel>
        get() = NewsViewModel::class.java

    lateinit var newsAdapter: NewsPageAdapter

    override fun onInitializeView() {
        super.onInitializeView()

        newsAdapter = NewsPageAdapter(requireContext())

        newsAdapter.onClick = {
            //
        }

        recycler_view_news.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun onSubscribe() {
        super.onSubscribe()

        lifecycleScope.launch {
            viewModel.fetchNews("test")?.collectLatest {
                newsAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            newsAdapter.loadStateFlow.collectLatest { loadStates ->
                swipe_refresh_layout_news.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }
}