package com.zdez.coder.feature_user_list.domain.util

sealed class SortType(val sort: String) {
    object FirstName : SortType("firstName")
    object Birthday : SortType("birthday")
}