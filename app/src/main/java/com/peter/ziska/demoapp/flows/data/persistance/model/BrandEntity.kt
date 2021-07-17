package com.peter.ziska.demoapp.flows.data.persistance.model

import kotlinx.serialization.Serializable

@Serializable
data class BrandEntity (
	val id : Int,
	val name : String
)