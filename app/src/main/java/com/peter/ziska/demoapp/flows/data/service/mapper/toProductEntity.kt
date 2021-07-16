package com.peter.ziska.demoapp.flows.data.service.mapper

import com.peter.ziska.demoapp.flows.data.service.model.BrandDto
import com.peter.ziska.demoapp.flows.data.service.model.PriceDto
import com.peter.ziska.demoapp.flows.data.service.model.ProductDto

fun ProductDto.toProductEntity() = ProductDto(
    id,
    productId,
    masterId,
    PriceDto(price.value, price.currency),
    name,
    BrandDto(brand.id, brand.name),
    annotation,
    orderUnit,
    attributes,
    imageUrl,
    stockAvailability,
    reviewSummary
)