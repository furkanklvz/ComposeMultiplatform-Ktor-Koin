package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: String = "unknown",
    val name: String = "unknown"
)