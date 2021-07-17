package com.peter.ziska.demoapp.flows.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewSummary(
    val score: Int
)