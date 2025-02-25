package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable
import org.klavs.demo.data.entities.ImageSetX

@Serializable
data class Service(
    val homePage: String = "unknown",
    val id: String = "unknown",
    val imageSet: ImageSetX = ImageSetX(),
    val name: String = "unknown",
    val themeColorCode: String = "unknown"
)