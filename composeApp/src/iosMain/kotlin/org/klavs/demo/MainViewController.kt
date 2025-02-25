package org.klavs.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
        App()
    }
}