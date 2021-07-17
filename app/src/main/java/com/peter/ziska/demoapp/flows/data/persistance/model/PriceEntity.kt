package com.peter.ziska.demoapp.flows.data.persistance.model

import kotlinx.serialization.Serializable

@Serializable
data class PriceEntity (
	val value : Int,
	val currency : String
)