package com.zdez.coder.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.ServiceLocator
import com.zdez.coder.navigation.Screen
import com.zdez.coder.people.UserSortingType

@Composable
fun MainScreen(navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf(UserSortingType.FirstName.sorting) }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val usersRepository = ServiceLocator.provideUsersRepository(context)

    val viewModelFactory = MainViewModelFactory(usersRepository)
    val viewModel = ViewModelProvider(
        LocalViewModelStoreOwner.current!!,
        viewModelFactory
    ).get(MainViewModel::class.java)

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = { textForSearch = it },
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
                        }) {
                            Text(text = stringResource(R.string.Sorting_by_alphabet))
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = UserSortingType.Birthday.sorting

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
                .fillMaxHeight()
        ) {
            //TODO Tabs
            ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
                viewModel.tabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = { selectedTabIndex.value = index },
                        text = { Text(text = tab) }
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.isDataLoadingError.value == false) {
                    navController.navigate(Screen.People.route)
                    viewModel.isDataLoadingError.value = null
                } else {
                    Image(Icons.Filled.Warning, contentDescription = stringResource(R.string.Warning))
                    Text(text = stringResource(R.string.load_error_begin))
                    Text(text = stringResource(R.string.load_error_end))
                    TextButton(
                        onClick = { viewModel.downloadUsers() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = stringResource(R.string.try_again))
                    }
                }
            }
        }
    }
}