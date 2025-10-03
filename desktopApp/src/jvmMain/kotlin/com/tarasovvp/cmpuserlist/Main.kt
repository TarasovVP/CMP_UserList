package com.tarasovvp.cmpuserlist

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.tarasovvp.cmpuserlist.di.initKoin
import com.tarasovvp.cmpuserlist.presentation.App

fun main() {
    initKoin()
    application {
        Window(onCloseRequest = ::exitApplication, title = APP_NAME) {
            App()
        }
    }
}

private const val APP_NAME = "CMP User List"