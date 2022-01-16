package com.zdez.coder.people

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.zdez.coder.data.PeopleDatabase

@Composable
fun PeopleScreen(navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    val order = remember { mutableStateOf("firstName") }
    var textForSearch by remember { mutableStateOf("") }
    val selectedTabIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val dataSource = PeopleDatabase.getInstance(context).peopleDao
    val viewModelFactory = PeopleViewModelFactory(dataSource)
    val viewModel = ViewModelProvider(LocalViewModelStoreOwner.current!!,
        viewModelFactory).get(PeopleViewModel::class.java)

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
                        Icon(Icons.Filled.Menu, contentDescription = "Sorting")
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = "firstName"
                            viewModel.getPeople(order.value)
                        }) {
                            Text(text = "По алфавиту")
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            order.value = "birthday"
                            viewModel.getPeople(order.value)
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
                viewModel.tabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = {
                            selectedTabIndex.value = index
                            if(selectedTabIndex.value==0){
                                viewModel.getPeople(order.value)
                            }else{
                                viewModel.getPeopleInDepartment(department = tab, order.value)
                            }

                        },
                        text = { Text(text = tab) }
                    )
                }
            }
            LazyColumn(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(viewModel.people) { user ->
                    Column() {
                        Text(text = user.firstName)
                        Text(text = user.lastName)
                        Text(text = user.avatarUrl)
                        Text(text = user.department)
                    }
                }
            }
            //TODO Snackbars
        }
    }
}