package io.github.chenglei6arch.springmotionlab.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Spring Motion Lab",
    ) {
        SpringLabScreen()
    }
}
