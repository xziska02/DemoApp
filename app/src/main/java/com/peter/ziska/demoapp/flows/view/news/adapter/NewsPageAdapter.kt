package com.peter.ziska.demoapp.flows.view.news.adapter

import android.app.Application
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.extension.inflate
import com.peter.ziska.demoapp.flows.domain.model.Article
import kotlinx.android.synthetic.main.list_item_news.view.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPageAdapter @Inject constructor(private val context: Context) :
    PagingDataAdapter<Article, NewsPageAdapter.NewsViewHolder>(NewsComparator) {

    lateinit var onClick: (Article) -> Unit

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(parent.inflate(R.layout.list_item_news))

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article?) {
            article?.run {
                itemView.setOnClickListener { onClick(this) }
                itemView.text_view_title.text = title
                itemView.text_view_description.text = description
                Glide.with(context).load(this.urlToImage).into(itemView.image_view_news)
            }
        }
    }

    object NewsComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem

    }
}