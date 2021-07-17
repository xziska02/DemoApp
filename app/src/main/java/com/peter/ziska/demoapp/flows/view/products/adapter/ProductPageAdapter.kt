package com.peter.ziska.demoapp.flows.view.products.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.extension.inflate
import com.peter.ziska.demoapp.flows.domain.model.Product
import kotlinx.android.synthetic.main.list_item_product.view.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductPageAdapter @Inject constructor(private val context: Context) :
    PagingDataAdapter<Product, ProductPageAdapter.ProductViewHolder>(ProductComparator) {

    lateinit var onClick: (Product) -> Unit
    lateinit var onAddToBasket: (Product) -> Unit
    lateinit var onLikeClicked: (Product) -> Unit

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product?, position: Int) {
            item?.run {
                itemView.setOnClickListener { onClick(this) }
                itemView.text_view_title.text = name
                itemView.text_view_description.text = this.annotation
                itemView.text_view_manufacturer.text = this.brand.name
                itemView.button_add_basket.setOnClickListener {
                    onAddToBasket(this)
                }
                itemView.rating_bar.rating = reviewSummary.score.toFloat()
                itemView.button_like_product.setOnClickListener {
                    this.isLiked = !this.isLiked
                    onLikeClicked(this)
                    notifyItemChanged(position)
                }

                Locale.setDefault(Locale.forLanguageTag("CZ"))
                itemView.text_view_amount.text = "${price.value} ${Currency.getInstance(price.currency).symbol}"
                if (isLiked) {
                    itemView.button_like_product.setImageDrawable(context.getDrawable(R.drawable.ic_heart_fill))
                } else {
                    itemView.button_like_product.setImageDrawable(context.getDrawable(R.drawable.ic_heart_empty))
                }
                Glide.with(context).load("https://i.notino.com/detail_zoom/${this.imageUrl}")
                    .into(itemView.image_view_product)
            }
        }
    }

    object ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            parent.inflate(
                R.layout.list_item_product
            )
        )
}