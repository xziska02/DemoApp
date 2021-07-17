package com.peter.ziska.demoapp.flows.data.persistance.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "products")
@Serializable
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val productId: Int,
    val masterId: Int,
    val price: PriceEntity,
    val name: String,
    val brand: BrandEntity,
    val annotation: String,
    val orderUnit: String,
    val imageUrl: String,
    val reviewSummary: ReviewSummaryEntity,
    val isLiked: Boolean = false,
)