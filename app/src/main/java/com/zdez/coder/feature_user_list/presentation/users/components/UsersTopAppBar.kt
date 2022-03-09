package com.zdez.coder.feature_user_list.presentation.users.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.domain.util.OrderType
import com.zdez.coder.feature_user_list.domain.util.UserOrder
import com.zdez.coder.feature_user_list.presentation.users.UsersViewModel

@Composable
fun UsersTopAppBar(viewModel: UsersViewModel, userOrder: UserOrder) {
    val expanded = remember { mutableStateOf(false) }
    var textForSearch by remember { mutableStateOf("") }
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