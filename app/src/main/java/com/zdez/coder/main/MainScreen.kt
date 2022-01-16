package com.zdez.coder.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.zdez.coder.R
import com.zdez.coder.data.PeopleDatabase
import com.zdez.coder.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf("firstName") }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val dataSource = PeopleDatabase.getInstance(context).peopleDao
    val viewModelFactory = MainViewModelFactory(dataSource)
    val viewModel = ViewModelProvider(LocalViewModelStoreOwner.current!!,
        viewModelFactory).get(MainViewModel::class.java)

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
                        Icon(painterResource(id = R.drawable.ic_search_icon), contentDescription = "Sorting")
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
            Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                if (!viewModel.isFailed.value) {
                    Text(text = "Какой-то сверхразум все сломал")
                    Text(text = "Постараемся быстро починить")
                    TextButton(onClick = { viewModel.getPeople() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)) {
                        Text(text = "Попробовать снова")
                    }

                } else {
                    navController.navigate(Screen.People.route)
                    viewModel.isFailed.value = false
                }
            }
        }
    }
}