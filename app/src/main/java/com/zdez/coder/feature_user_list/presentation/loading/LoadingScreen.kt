package com.zdez.coder.feature_user_list.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.presentation.util.Screen
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun LoadingScreen(navController: NavController, viewModel: LoadingViewModel = hiltViewModel()) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf("firstName") }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = { textForSearch = it },
                    modifier = Modifier.fillMaxWidth(1f),
                    placeholder = { Text(text = "Введите имя, фамилию или тег") }
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search textField",
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                        .fillMaxWidth(0.90f))
            },
            actions = {
                Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(painterResource(id = R.drawable.ic_search_icon),
                            contentDescription = "Sorting")
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = "firstName"
                        }) {
                            Text(text = "По алфавиту")
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = "birthday"

                        }) {
                            Text(text = "По дате рождения")
                        }
                    }
                }
            }
        )
    }
    ) {
        Column(verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
                Tabs.listTabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = { selectedTabIndex.value = index },
                        text = { Text(text = tab) }
                    )
                }
            }
            when (viewModel.users.error) {
                UsersData.ErrorUserData.NetworkError -> ErrorScreen(viewModel)
                else -> if (!viewModel.users.loading) {
                    navController.navigate(Screen.Users.route)
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(viewModel: LoadingViewModel) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(Icons.Filled.Warning, contentDescription = "Warning")
        Text(text = "Какой-то сверхразум все сломал")
        Text(text = "Постараемся быстро починить")
        TextButton(onClick = { viewModel.loadingUsers() },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)) {
            Text(text = "Попробовать снова")
        }
    }
}
