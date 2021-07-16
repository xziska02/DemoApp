package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributesDto (
	@SerialName("Master")
	val master : Boolean,
	@SerialName("EAN")
	val ean : String,
	@SerialName("PackageSize")
	val packageSize : PackageSizeDto,
	@SerialName("Niche")
	val niche : Boolean? = null
)