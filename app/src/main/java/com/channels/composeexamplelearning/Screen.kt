package com.channels.composeexamplelearning

sealed class Screen(var route : String) {
    object MainScreen : Screen("MainScreen")
    object DetailScreen : Screen("DetailScreen")
}