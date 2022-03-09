package com.zdez.coder.feature_user_list.presentation.users.components

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.zdez.coder.feature_user_list.domain.util.UserOrder
import com.zdez.coder.feature_user_list.presentation.users.UsersViewModel
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun UsersTabsRow(viewModel: UsersViewModel, userOrder: UserOrder) {

    val selectedTabIndex = remember { mutableStateOf(0) }
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
}