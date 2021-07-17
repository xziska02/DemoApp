package com.peter.ziska.demoapp.flows.data.persistance.mapper

import com.peter.ziska.demoapp.flows.data.persistance.model.ProductEntity
import com.peter.ziska.demoapp.flows.domain.model.Brand
import com.peter.ziska.demoapp.flows.domain.model.Price
import com.peter.ziska.demoapp.flows.domain.model.Product
import com.peter.ziska.demoapp.flows.domain.model.ReviewSummary

fun ProductEntity.toProduct() = Product(
    id,
    productId,
    masterId,
    Price(price.value, price.currency),
    name,
    Brand(brand.id, brand.name),
    annotation,
    orderUnit,
    imageUrl,
    ReviewSummary(reviewSummary.score),
    isLiked,
)
