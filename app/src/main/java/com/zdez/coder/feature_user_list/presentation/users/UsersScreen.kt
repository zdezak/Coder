package com.zdez.coder.feature_user_list.presentation.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.domain.util.OrderType
import com.zdez.coder.feature_user_list.domain.util.UserOrder
import com.zdez.coder.feature_user_list.presentation.users.components.UserItem
import com.zdez.coder.feature_user_list.presentation.util.Screen
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun UsersScreen(
    navController: NavController,
    userOrder: UserOrder = UserOrder.FirstName(OrderType.Ascending),
    viewModel: UsersViewModel = hiltViewModel()
) {
    val expanded = remember { mutableStateOf(false) }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = {
                        textForSearch = it
                        viewModel.fieldSearch(textForSearch, userOrder)
                    },
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
                        .fillMaxWidth(0.90f)
                )
            },
            actions = {
                Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(
                            painterResource(id = R.drawable.ic_search_icon),
                            contentDescription = stringResource(R.string.sorting)
                        )
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            viewModel.sortBy(UserOrder.FirstName(OrderType.Ascending))
                        }) {
                            Text(text = stringResource(R.string.sorting_by_alphabet))
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            viewModel.sortBy(UserOrder.Birthday(OrderType.Descending))
                        }) {
                            Text(text = stringResource(R.string.sotring_by_birthday))
                        }
                    }
                }
            }
        )
    }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
                Tabs.listTabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = {
                            selectedTabIndex.value = index
                            if (selectedTabIndex.value == 0) {
                                viewModel.getUsers(userOrder)
                            } else {
                                viewModel.getUsersInDepartment(department = tab, userOrder)
                            }

                        },
                        text = { Text(text = tab) }
                    )
                }
            }
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
}