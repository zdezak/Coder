package com.zdez.coder.feature_user_list.presentation.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val users: UsersData = UsersData(loading = true, data = listOf(),error = null)
    fun loadingUsers() {
        viewModelScope.launch {
           users.data =  repository.addUsers()
        }
    }

}

data class UsersData(
    var loading: Boolean,
    var data: List<User>,
    var error: ErrorUserData?,
) {
    sealed class ErrorUserData() {
        object NetworkError : ErrorUserData()
    }
}


