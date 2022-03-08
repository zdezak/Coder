package com.zdez.coder.feature_user_list.presentation.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun UsersScreen(navController: NavController, viewModel: UsersViewModel = hiltViewModel()) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf("firstName") }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = {
                        textForSearch = it
                        viewModel.fieldSearch(textForSearch, order.value)
                    },
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
                            viewModel.getUsers(order.value)
                        }) {
                            Text(text = "По алфавиту")
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = "birthday"
                            viewModel.getUsers(order.value)
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
                .fillMaxSize()
        ) {
            //TODO Tabs
            ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
                Tabs.listTabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = {
                            selectedTabIndex.value = index
                            if (selectedTabIndex.value == 0) {
                                viewModel.getUsers(order.value)
                            } else {
                                viewModel.getUsersInDepartment(department = tab, order.value)
                            }

                        },
                        text = { Text(text = tab) }
                    )
                }
            }
            LazyColumn(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(viewModel.users) { user ->
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painterResource(id = R.drawable.ic_launcher_background),
                            //painter = rememberGlidePainter(user.avatarUrl),
                            contentDescription = user.firstName + " " + user.lastName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, Color.Black, CircleShape)
                        )
                        Column() {
                            Row() {
                                Text(text = user.firstName)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = user.lastName)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = user.userTag)
                            }
                            Text(text = user.department)
                        }
                    }
                }
            }
            //TODO Snackbars
        }
    }
}