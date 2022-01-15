package com.zdez.coder.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zdez.coder.main.MainScreen
import com.zdez.coder.people.PeopleScreen
import com.zdez.coder.profile.ProfileScreen

@Composable
fun PointOfEntry(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { MainScreen(navController = navController) }
        composable(Screen.People.route) { PeopleScreen(navController = navController) }
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