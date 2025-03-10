package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class VerticalBackdrop(
    val w240: String = "unknown",
    val w360: String = "unknown",
    val w480: String = "unknown",
    val w600: String = "unknown",
    val w720: String = "unknown"
)