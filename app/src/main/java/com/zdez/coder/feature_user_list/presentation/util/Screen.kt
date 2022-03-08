package com.zdez.coder.feature_user_list.presentation.util

sealed class Screen(val route: String) {
    object Loading : Screen("loading_screen")
    object Users : Screen("users_screen")
    object Profile : Screen("profile_screen")
}