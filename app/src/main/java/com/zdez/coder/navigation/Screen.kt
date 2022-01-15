package com.zdez.coder.navigation

sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object People: Screen("people_screen")
    object Profile: Screen("profile_screen")
}