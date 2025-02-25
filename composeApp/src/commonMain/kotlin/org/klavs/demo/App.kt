package org.klavs.demo

import androidx.compose.runtime.Composable
import org.klavs.demo.di.appModule
import org.klavs.demo.view.Navigation
import org.koin.compose.KoinApplication


@Composable
fun App() {
    KoinApplication(
        application = {modules(appModule)}
    ){
        Navigation()
    }
}