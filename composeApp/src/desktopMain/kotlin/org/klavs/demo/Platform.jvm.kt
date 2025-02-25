package org.klavs.demo

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


class JVMPlatform: Platform {
    override val name: String = "desktop"
    override fun log(tag: String, message: String) {

    }
    @Composable
    override fun RightArrowForScrolling(action: () -> Unit) {
        FilledIconButton(onClick = action){
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                 contentDescription = "arrow back")
        }
    }
    @Composable
    override fun LeftArrowForScrolling(action: () -> Unit) {
        FilledIconButton(onClick = action){
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                contentDescription = "arrow forward")
        }
    }

    @Composable
    override fun VerticalScrollBar(modifier: Modifier, listState: LazyListState) {
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(listState),
            modifier = modifier
        )
    }
}



actual fun getPlatform(): Platform = JVMPlatform()