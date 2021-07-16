package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable


@Serializable
data class ProductDto(
    val id: Int,
    val productId: Int,
    val masterId: Int,
    val price: PriceDto,
    val name: String,
    val brand: BrandDto,
    val annotation: String,
    val orderUnit: String,
    val attributes: AttributesDto,
    val imageUrl: String,
    val stockAvailability: StockAvailabilityDto,
    val reviewSummary: ReviewSummaryDto
)