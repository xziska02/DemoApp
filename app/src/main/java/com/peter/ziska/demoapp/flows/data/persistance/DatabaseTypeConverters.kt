package com.peter.ziska.demoapp.flows.data.persistance

import androidx.room.TypeConverter
import com.peter.ziska.demoapp.flows.data.persistance.model.BrandEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.PriceEntity
import com.peter.ziska.demoapp.flows.data.persistance.model.ReviewSummaryEntity
import kotlinx.serialization.json.Json

class DatabaseTypeConverters {

    @TypeConverter
    fun priceEntityToString(priceEntity: PriceEntity): String =
        Json.encodeToString(PriceEntity.serializer(), priceEntity)

    @TypeConverter
    fun stringToPriceEntity(value: String): PriceEntity =
        Json.decodeFromString(PriceEntity.serializer(), value)

    @TypeConverter
    fun brandEntityToString(brandEntity: BrandEntity): String =
        Json.encodeToString(BrandEntity.serializer(), brandEntity)

    @TypeConverter
    fun stringToBrandEntity(value: String): BrandEntity =
        Json.decodeFromString(BrandEntity.serializer(), value)

    @TypeConverter
    fun reviewSummaryEntityToString(reviewEntity: ReviewSummaryEntity): String =
        Json.encodeToString(ReviewSummaryEntity.serializer(), reviewEntity)

    @TypeConverter
    fun stringToReviewSummaryEntity(value: String): ReviewSummaryEntity =
        Json.decodeFromString(ReviewSummaryEntity.serializer(), value)


}