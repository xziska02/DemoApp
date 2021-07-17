package com.peter.ziska.demoapp.flows.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val productId: Int,
    val masterId: Int,
    val price: Price,
    val name: String,
    val brand: Brand,
    val annotation: String,
    val orderUnit: String,
    val imageUrl: String,
    val reviewSummary: ReviewSummary,
    var isLiked: Boolean = false,
)