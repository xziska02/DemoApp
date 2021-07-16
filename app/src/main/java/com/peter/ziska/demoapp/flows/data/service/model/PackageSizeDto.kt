package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable

@Serializable
data class PackageSizeDto (
	val height : Int,
	val width : Int,
	val depth : Int
)