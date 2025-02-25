package org.klavs.demo


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = "ios"
    override fun log(tag: String, message: String) {
    }
    @Composable
    override fun RightArrowForScrolling(action: () -> Unit) {}
    @Composable
    override fun LeftArrowForScrolling(action: () -> Unit) {}

    @Composable
    override fun VerticalScrollBar(modifier: Modifier, listState: LazyListState) {}

}

actual fun getPlatform(): Platform = IOSPlatform()