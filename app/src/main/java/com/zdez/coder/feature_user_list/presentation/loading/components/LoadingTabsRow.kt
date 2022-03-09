package com.zdez.coder.feature_user_list.presentation.loading.components

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.zdez.coder.feature_user_list.presentation.util.Tabs

@Composable
fun LoadingTabsRow(startIndex: Int) {
    val selectedTabIndex = remember { mutableStateOf(startIndex) }
    ScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
        Tabs.listTabs.forEachIndexed { index, tab ->
            Tab(
                selected = index == selectedTabIndex.value,
                onClick = { selectedTabIndex.value = index },
                text = { Text(text = tab) }
            )
        }
    }
}