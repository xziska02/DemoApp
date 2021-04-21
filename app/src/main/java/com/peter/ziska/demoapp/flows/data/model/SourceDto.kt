package com.peter.ziska.demoapp.flows.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)