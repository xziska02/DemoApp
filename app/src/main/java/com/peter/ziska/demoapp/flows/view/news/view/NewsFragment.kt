package com.peter.ziska.demoapp.flows.view.news.view

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.extension.hideKeyboard
import com.peter.ziska.demoapp.flows.view.news.adapter.NewsPageAdapter
import com.peter.ziska.demoapp.flows.view.news.navigation.NewsNavigator
import com.peter.ziska.demoapp.flows.view.news.presenter.NewsViewModel
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsFragment : BaseFragment<NewsViewModel>(R.layout.news_fragment),
    SearchView.OnQueryTextListener {

    override val viewModelClass: Class<NewsViewModel>
        get() = NewsViewModel::class.java

    lateinit var newsAdapter: NewsPageAdapter

    lateinit var searchView: SearchView

    @Inject
    lateinit var newsNavigator: NewsNavigator

    override fun onInitializeView() {
        super.onInitializeView()
        setHasOptionsMenu(true)

        newsAdapter = NewsPageAdapter(requireContext())
        newsNavigator.init(findNavController())

        newsAdapter.onClick = {
            newsNavigator.navigateToNewsDetail(it)
        }

        recycler_view_news.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun onSubscribe() {
        super.onSubscribe()

        lifecycleScope.launch {
            newsAdapter.loadStateFlow.collectLatest { loadStates ->
                swipe_refresh_layout_news.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun fetchNews(query: String?) {
        lifecycleScope.launch {
            viewModel.fetchNews(query)?.collectLatest {
                newsAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        fetchNews(query)
        requireContext().hideKeyboard(searchView)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}