package com.zdez.coder.feature_user_list.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.feature_user_list.domain.util.OrderType
import com.zdez.coder.feature_user_list.domain.util.UserOrder
import com.zdez.coder.feature_user_list.presentation.components.DefaultTopAppBar
import com.zdez.coder.feature_user_list.presentation.users.components.UserItem
import com.zdez.coder.feature_user_list.presentation.users.components.UsersTabsRow
import com.zdez.coder.feature_user_list.presentation.util.Screen

@Composable
fun UsersScreen(
    navController: NavController,
    userOrder: UserOrder = UserOrder.FirstName(OrderType.Ascending),
    viewModel: UsersViewModel = hiltViewModel(),
) {


    Scaffold(topBar = {
        var textForSearch by remember { mutableStateOf("") }
        DefaultTopAppBar(value = textForSearch, readOnly = false, onValueChange = {
            textForSearch = it
            viewModel.fieldSearch(textForSearch, userOrder)
        },
            sorting = {
                viewModel.sortBy(UserOrder.FirstName(OrderType.Ascending))
            })
    }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
        ) {
            UsersTabsRow(viewModel, userOrder)
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(viewModel.users) { user ->
                    UserItem(
                        user = user,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate(Screen.Profile.route + user.id)
                            }
                    )
                }
            }
            //TODO Snackbars
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUsers(userOrder)
    })
}