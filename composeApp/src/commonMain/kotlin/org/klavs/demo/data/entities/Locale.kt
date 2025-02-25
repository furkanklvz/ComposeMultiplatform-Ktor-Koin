package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Locale(
    val language: String = "unknown"
)