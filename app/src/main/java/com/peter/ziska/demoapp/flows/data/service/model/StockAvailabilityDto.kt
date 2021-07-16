package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable

@Serializable
data class StockAvailabilityDto(
    val code: String,
    val count: String? = null
)