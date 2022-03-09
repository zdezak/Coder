package com.zdez.coder.feature_user_list.presentation.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.repository.Repository
import com.zdez.coder.feature_user_list.domain.util.OrderType
import com.zdez.coder.feature_user_list.domain.util.UserOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var users by mutableStateOf(listOf<User>())
        private set

    fun getUsers(userOrder: UserOrder) {
        viewModelScope.launch {
            users = repository.getUsers()
            sortBy(userOrder)
        }
    }

    fun sortBy(userOrder: UserOrder) {
        when(userOrder.orderType) {
            is OrderType.Ascending -> {
                when(userOrder) {
                    is UserOrder.FirstName -> users.sortedBy { it.firstName.lowercase() }
                    is UserOrder.Birthday -> users.sortedBy { it.birthday }
                }
            }
            is OrderType.Descending -> {
                when(userOrder) {
                    is UserOrder.FirstName -> users.sortedByDescending { it.firstName.lowercase() }
                    is UserOrder.Birthday -> users.sortedByDescending { it.birthday }
                }
            }
        }
    }

    fun updateUsers(userOrder: UserOrder) {
        viewModelScope.launch {
            users = repository.update()
            sortBy(userOrder)
        }
    }

    fun getUsersInDepartment(department: String, userOrder: UserOrder) {
        viewModelScope.launch {
            users = repository.getUsersInDepartment(department)
            sortBy(userOrder)
        }
    }

    fun fieldSearch(search: String, userOrder: UserOrder) {
        viewModelScope.launch {
            users = repository.searchUsers(search)
            sortBy(userOrder)
        }
    }
}