package org.klavs.demo

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
    fun log(tag: String, message: String)

    @Composable
    fun RightArrowForScrolling(action: () -> Unit)

    @Composable
    fun LeftArrowForScrolling(action: () -> Unit)

    @Composable
    fun VerticalScrollBar(modifier: Modifier, listState: LazyListState)
}

expect fun getPlatform(): Platform