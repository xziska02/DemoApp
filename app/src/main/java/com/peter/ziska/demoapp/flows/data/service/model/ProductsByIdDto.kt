package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsByIdDto (
	@SerialName("vpProductByIds")
	val products : List<ProductDto>
)