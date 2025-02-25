package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class ImageSetX(
    val darkThemeImage: String = "unknown",
    val lightThemeImage: String = "unknown",
    val whiteImage: String = "unknown"
)