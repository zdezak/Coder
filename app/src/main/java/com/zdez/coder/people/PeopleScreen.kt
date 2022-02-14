package com.zdez.coder.people

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.ServiceLocator

@Composable
fun PeopleScreen(navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf(UserSortingType.FirstName.sorting) }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val usersRepository = ServiceLocator.provideUsersRepository(context)
    val viewModelFactory = PeopleViewModelFactory(usersRepository)
    val viewModel = ViewModelProvider(
        LocalViewModelStoreOwner.current!!,
        viewModelFactory
    ).get(PeopleViewModel::class.java)

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
                    placeholder = { Text(text = stringResource(R.string.search_tip)) }
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(R.string.Search_Field),
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
                            contentDescription = stringResource(R.string.Sorting)
                        )
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = UserSortingType.FirstName.sorting
                            viewModel.getUsers(order.value)
                        }) {
                            Text(text = stringResource(R.string.Sorting_by_alphabet))
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = UserSortingType.Birthday.sorting
                            viewModel.getUsers(order.value)
                        }) {
                            Text(text = stringResource(R.string.Sorting_by_birthday))
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
            //TODO Tabs
            ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
                viewModel.tabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = {
                            selectedTabIndex.value = index
                            if (selectedTabIndex.value == 0) {
                                viewModel.getUsers(order.value)
                            } else {
                                viewModel.getPeopleInDepartment(department = tab, order.value)
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
                    Card {

                    }
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