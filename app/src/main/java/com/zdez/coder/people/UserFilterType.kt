package com.zdez.coder.people

sealed class UserFilterType(val filter: String){
    object FirstName : UserFilterType("firstName")
    object Birthday : UserFilterType("birthday")
}
