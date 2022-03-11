package com.zdez.coder.feature_user_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.AppTheme
import com.zdez.coder.core.util.TestTags
import com.zdez.coder.di.AppModule
import com.zdez.coder.feature_user_list.presentation.MainActivity
import com.zdez.coder.feature_user_list.presentation.loading.LoadingScreen
import com.zdez.coder.feature_user_list.presentation.profile.ProfileScreen
import com.zdez.coder.feature_user_list.presentation.users.UsersScreen
import com.zdez.coder.feature_user_list.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class UsersEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Loading.route
                ) {
                    composable(Screen.Loading.route) { LoadingScreen(navController = navController) }
                    composable(Screen.Users.route) { UsersScreen(navController = navController) }
                    composable(Screen.Profile.route + "/{id}", arguments = listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { entry ->
                        ProfileScreen(
                            id = entry.arguments!!.getString("id")!!,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Test
    fun loadingNewData(){
        composeRule
    }
    @Test
    fun loadingNewData_To_UsersScreen(){

    }
    @Test
    fun userScreen_To_ProfileScreen(){

    }
}