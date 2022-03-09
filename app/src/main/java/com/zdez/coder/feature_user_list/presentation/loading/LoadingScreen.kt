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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.presentation.util.Screen
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun LoadingScreen(navController: NavController, viewModel: LoadingViewModel = hiltViewModel()) {
    val expanded = remember { mutableStateOf(false) }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = { textForSearch = it },
                    modifier = Modifier.fillMaxWidth(1f),
                    placeholder = { Text(text = stringResource(R.string.discription_searchfield)) }
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_textfield),
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                        .fillMaxWidth(0.90f))
            },
            actions = {
                Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(painterResource(id = R.drawable.ic_search_icon),
                            contentDescription = stringResource(R.string.sorting)
                        )
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                        }) {
                            Text(text = stringResource(R.string.sorting_by_alphabet))
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false

                        }) {
                            Text(text = stringResource(R.string.sotring_by_birthday))
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
                Tabs.listTabs.forEachIndexed { index, tab ->
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
        Image(Icons.Filled.Warning, contentDescription = stringResource(R.string.warning))
        Text(text = stringResource(R.string.discription_error))
        Text(text = stringResource(R.string.promise_to_fix))
        TextButton(onClick = { viewModel.loadingUsers() },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}
