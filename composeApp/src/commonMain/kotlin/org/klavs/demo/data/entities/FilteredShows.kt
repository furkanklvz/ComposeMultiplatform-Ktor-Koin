package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class FilteredShows(
    val shows: List<ShowData>,
    val hasMore: Boolean = false,
    val nextCursor: String? = null
)
