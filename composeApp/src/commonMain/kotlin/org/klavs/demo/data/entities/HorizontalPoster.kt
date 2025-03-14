package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class HorizontalPoster(
    val w1080: String = "unknown",
    val w1440: String = "unknown",
    val w360: String = "unknown",
    val w480: String = "unknown",
    val w720: String = "unknown"
)