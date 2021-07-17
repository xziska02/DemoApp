package com.peter.ziska.demoapp.flows.data.persistance.mapper

import com.peter.ziska.demoapp.flows.data.persistance.model.BrandEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.PriceEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.ProductEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.ReviewSummaryEntity
import com.peter.ziska.demoapp.flows.domain.model.Product

fun Product.toProductEntity() = ProductEntity(
    id,
    productId,
    masterId,
    PriceEntity(price.value, price.currency),
    name,
    BrandEntity(brand.id, brand.name),
    annotation,
    orderUnit,
    imageUrl,
    ReviewSummaryEntity(reviewSummary.score),
    isLiked,
)
