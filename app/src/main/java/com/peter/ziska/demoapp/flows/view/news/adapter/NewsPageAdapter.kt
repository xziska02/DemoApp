package com.peter.ziska.demoapp.flows.view.news.adapter

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
import com.peter.ziska.demoapp.flows.domain.model.BaseArticle
import kotlinx.android.synthetic.main.list_item_article.view.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPageAdapter @Inject constructor(private val context: Context) :
    PagingDataAdapter<BaseArticle, NewsPageAdapter.BaseViewHolder>(ArticleComparator) {

    lateinit var onClick: (Article) -> Unit

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            BaseArticle.ArticleType.BASIC_ARTICLE -> BasicArticleViewHolder((parent.inflate(R.layout.list_item_article)))
            BaseArticle.ArticleType.GAME_ARTICLE -> BasicArticleViewHolder((parent.inflate(R.layout.list_item_article)))
            else -> BasicArticleViewHolder(parent.inflate(R.layout.list_item_article))
        }

    override fun getItemViewType(position: Int): Int =
        getItem(position)?.type ?: BaseArticle.ArticleType.BASIC_ARTICLE

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: BaseArticle?)
    }

    inner class BasicArticleViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bind(item: BaseArticle?) {
            (item as? Article)?.run {
                itemView.setOnClickListener { onClick(this) }
                itemView.text_view_title.text = title
                itemView.text_view_description.text = content
                Glide.with(context).load(urlToImage).into(itemView.image_view_news)
            }
        }
    }

    //TODO add different type of object to recyclerview
    inner class GameArticleViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bind(item: BaseArticle?) {
            (item as Article).run {
                itemView.setOnClickListener { onClick(this) }
                itemView.text_view_title.text = title
                itemView.text_view_description.text = content
                Glide.with(context).load(urlToImage).into(itemView.image_view_news)
            }
        }
    }

    object ArticleComparator : DiffUtil.ItemCallback<BaseArticle>() {
        override fun areItemsTheSame(oldItem: BaseArticle, newItem: BaseArticle): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BaseArticle, newItem: BaseArticle): Boolean =
            oldItem.id == newItem.id
    }
}