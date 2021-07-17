package com.peter.ziska.demoapp.flows.data.persistance.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewSummaryEntity (
	val score : Int
)