package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable
import org.klavs.demo.data.entities.Locale

@Serializable
data class Subtitle(
    val closedCaptions: Boolean = false,
    val locale: Locale = Locale("en")
)