package com.zdez.coder.feature_user_list.presentation.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.core.util.TestTags
import com.zdez.coder.feature_user_list.domain.model.UsersData
import com.zdez.coder.feature_user_list.presentation.loading.components.ErrorScreen
import com.zdez.coder.feature_user_list.presentation.loading.components.LoadingTabsRow
import com.zdez.coder.feature_user_list.presentation.loading.components.LoadingTopAppBar
import com.zdez.coder.feature_user_list.presentation.util.Screen

@Composable
fun LoadingScreen(navController: NavController, viewModel: LoadingViewModel = hiltViewModel()) {

    Scaffold(topBar = {
        LoadingTopAppBar()
    }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            LoadingTabsRow(0)
            when (viewModel.users.value.error) {
                UsersData.ErrorUserData.NetworkError -> ErrorScreen(viewModel)
                else -> if (viewModel.users.value.loading) {
                    CircularProgressIndicator(modifier = Modifier.testTag(TestTags.CIRCULAR_PROGRESS_INDICATOR))
                } else {
                    navController.navigate(Screen.Users.route)
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.clearDatabase()
        viewModel.loadingUsers()
    })
}
