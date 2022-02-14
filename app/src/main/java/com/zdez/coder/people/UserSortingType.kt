package com.zdez.coder.people

sealed class UserSortingType(val sorting: String) {
    object FirstName : UserSortingType("firstName")
    object Birthday : UserSortingType("birthday")
}
