package org.mywebsite.spring_motion_lab

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "spring-motion-lab",
    ) {
        App()
    }
}