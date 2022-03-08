package com.zdez.coder.feature_user_list.presentation.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var users by mutableStateOf(listOf<User>())
        private set

    fun getUsers(order: String) {
        viewModelScope.launch {
            users = repository.getUsers(order)
        }
    }

    fun updateUsers(order: String) {
        viewModelScope.launch {
            users = repository.update()
        }
    }
    fun getUsersInDepartment(department: String, order: String) {
        viewModelScope.launch {
            users = repository.getUsersInDepartment(department,order)
        }
    }

    fun fieldSearch(search: String, sort: String) {
        viewModelScope.launch {
            repository.searchUsers(search)
            users.sortedBy {
                if (sort == "firstName")
                    it.firstName
                else
                    it.birthday
            }
        }
    }
}