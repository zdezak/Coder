package com.zdez.coder.feature_user_list.presentation.util

sealed class Screen(val route: String) {
    object Loading : Screen("loading_screen")
    object People : Screen("people_screen")
    object Profile : Screen("profile_screen")
}