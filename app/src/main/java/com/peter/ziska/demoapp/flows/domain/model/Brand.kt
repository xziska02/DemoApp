package com.peter.ziska.demoapp.flows.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    val id: Int,
    val name: String
)