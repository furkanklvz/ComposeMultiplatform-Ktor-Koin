package org.klavs.demo

import android.os.Build
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class AndroidPlatform : Platform {
    override val name: String = "android"
    override fun log(tag: String, message: String) {
        Log.e(tag, message)
    }

    @Composable
    override fun RightArrowForScrolling(action: () -> Unit) {}

    @Composable
    override fun LeftArrowForScrolling(action: () -> Unit) {}

    @Composable
    override fun VerticalScrollBar(modifier: Modifier, listState: LazyListState) {}
}

actual fun getPlatform(): Platform = AndroidPlatform()