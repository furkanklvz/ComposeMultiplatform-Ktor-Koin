package org.klavs.demo.data.entities

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home: Routes()
}