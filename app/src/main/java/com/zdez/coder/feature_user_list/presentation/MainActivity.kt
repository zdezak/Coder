package com.zdez.coder.feature_user_list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.AppTheme
import com.zdez.coder.feature_user_list.presentation.loading.LoadingScreen
import com.zdez.coder.feature_user_list.presentation.util.Screen
import com.zdez.coder.feature_user_list.presentation.profile.ProfileScreen
import com.zdez.coder.feature_user_list.presentation.users.UsersScreen

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Loading.route) {
                        composable(Screen.Loading.route) { LoadingScreen(navController = navController) }
                        composable(Screen.People.route) { UsersScreen(navController = navController) }
                        composable(Screen.Profile.route + "/{id}", arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                            }
                        )) { entry ->
                            ProfileScreen(id = entry.arguments!!.getString("id")!!,
                                navController = navController)
                        }
                    }
                }
            }
        }
    }
}
