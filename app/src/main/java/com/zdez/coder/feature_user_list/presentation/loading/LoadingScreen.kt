package com.zdez.coder.feature_user_list.presentation.loading

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.core.util.TestTags
import com.zdez.coder.feature_user_list.domain.model.UsersData
import com.zdez.coder.feature_user_list.presentation.loading.components.ErrorScreen
import com.zdez.coder.feature_user_list.presentation.loading.components.LoadingTabsRow
import com.zdez.coder.feature_user_list.presentation.util.Screen

@Composable
fun LoadingScreen(navController: NavController, viewModel: LoadingViewModel = hiltViewModel()) {

    val expanded = remember { mutableStateOf(false) }
    var textForSearch by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextField(
                    value = textForSearch,
                    onValueChange = { textForSearch = it },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(1f),
                    placeholder = { Text(text = stringResource(com.zdez.coder.R.string.discription_searchfield)) }
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(com.zdez.coder.R.string.search_textfield),
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                        .fillMaxWidth(0.90f)
                )
            },
            actions = {
                Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(
                            painterResource(id = com.zdez.coder.R.drawable.ic_search_icon),
                            contentDescription = stringResource(com.zdez.coder.R.string.sorting)
                        )
                    }
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                        }) {
                            Text(text = stringResource(com.zdez.coder.R.string.sorting_by_alphabet))
                        }
                        DropdownMenuItem(onClick = {
                            expanded.value = false

                        }) {
                            Text(text = stringResource(com.zdez.coder.R.string.sotring_by_birthday))
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
            LoadingTabsRow(0)
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (viewModel.users.value.loading) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .testTag(TestTags.CIRCULAR_PROGRESS_INDICATOR))
                } else {
                    when (viewModel.users.value.error) {
                        UsersData.ErrorUserData.NetworkError -> ErrorScreen(viewModel)
                        else -> navController.navigate(Screen.Users.route)
                    }
                }
            }
        }
    }
}
