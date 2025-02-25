package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Audio(
    val language: String = "unknown"
)