package com.peter.ziska.demoapp.flows.view.detail.view

import com.bumptech.glide.Glide
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.view.detail.presenter.NewsDetailViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : BaseFragment<NewsDetailViewModel>(R.layout.fragment_news_detail) {

    override val viewModelClass: Class<NewsDetailViewModel>
        get() = NewsDetailViewModel::class.java

    override fun onInitializeView() {
        super.onInitializeView()

        setHasOptionsMenu(false)
    }

    override fun onSubscribe() {
        super.onSubscribe()

        viewModel.viewState.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(viewState: NewsDetailViewState) {
        when (viewState) {
            is NewsDetailViewState.NewsDetailArticle -> handleArticle(viewState.article)
        }
    }

    private fun handleArticle(article: Article) {
        with(article) {
            text_view_title.text = title
            text_view_author.text =
                String.format(requireContext().getString(R.string.written_by), author)
            text_view_content.text = content
            Glide.with(requireContext()).load(this.urlToImage).into(image_view_article)
        }
    }
}