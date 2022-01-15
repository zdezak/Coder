package com.zdez.coder.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {

    var textForSearch by remember {mutableStateOf("")    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = { textForSearch = it })
            },
            navigationIcon = {
                Icon(Icons.Filled.Search, contentDescription = "Search textField")
            }
            //TODO Actions
        )
    }
    ) {
        Column(verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            //TODO Tabs
            LazyColumn(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

            }
        }
        //TODO Snackbars
    }
}