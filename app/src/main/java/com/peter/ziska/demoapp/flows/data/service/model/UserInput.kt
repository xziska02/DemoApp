package com.peter.ziska.demoapp.flows.data.service.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInput(
    val q: String?,
    val lang: String?,
    val from: String?,
    val sort_by: String?,
    val page: Int?,
    val size: Int?
)