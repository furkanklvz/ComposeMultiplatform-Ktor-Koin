package org.klavs.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.awt.Dimension


fun main() = application {
    Window(
        state = rememberWindowState(size = DpSize(1100.dp, 900.dp)),
        onCloseRequest = ::exitApplication,
        title = "ComposeDemo",
    ) {
        window.minimumSize= Dimension(500.dp.value.toInt(), 900.dp.value.toInt())
        MaterialTheme {
            App()
        }
    }
}

@Preview
@Composable
private fun preview(){
    Text("dsf")
}