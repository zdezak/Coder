package com.zdez.coder.feature_user_list.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.domain.util.OrderType
import com.zdez.coder.feature_user_list.domain.util.UserOrder

@Composable
fun DefaultTopAppBar(
    value: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    sorting: (UserOrder) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            TextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                readOnly = readOnly,
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
                        sorting(UserOrder.FirstName(OrderType.Ascending))
                        //viewModel.sortBy(UserOrder.FirstName(OrderType.Ascending))
                    }) {
                        Text(text = stringResource(R.string.sorting_by_alphabet))
                    }
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        sorting(UserOrder.Birthday(OrderType.Descending))
                        //viewModel.sortBy(UserOrder.Birthday(OrderType.Descending))
                    }) {
                        Text(text = stringResource(R.string.sotring_by_birthday))
                    }
                }
            }
        }
    )
}