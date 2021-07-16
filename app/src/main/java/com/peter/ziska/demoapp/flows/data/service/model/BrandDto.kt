package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable

@Serializable
data class BrandDto (
	val id : Int,
	val name : String
)