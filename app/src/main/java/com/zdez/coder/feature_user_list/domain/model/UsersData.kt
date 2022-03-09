package com.zdez.coder.feature_user_list.domain.model

data class UsersData(
    var loading: Boolean = true,
    var data: List<User>,
    var error: ErrorUserData? = null,
) {
    sealed class ErrorUserData {
        object NetworkError : ErrorUserData()
    }
}