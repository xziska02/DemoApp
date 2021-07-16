package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable

@Serializable
data class PriceDto (
	val value : Int,
	val currency : String
)