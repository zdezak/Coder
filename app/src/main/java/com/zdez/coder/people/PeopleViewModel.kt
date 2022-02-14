package com.zdez.coder.people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersRepository
import kotlinx.coroutines.launch

class PeopleViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    private var currentFiltering = UserSortingType.FirstName.sorting
    val tabs = Tabs().listTabs
    val isDataLoadingError = mutableStateOf<Boolean?>(null)
    var users by mutableStateOf(listOf<User>())
        private set

    init {
        getUsers(UserSortingType.FirstName.sorting)
    }

    fun getUsers(order: String) {
        viewModelScope.launch {
            val result = usersRepository.getUsers(order)
            if (result is Result.Success) {
                isDataLoadingError.value = false
                users = result.data
            } else {
                isDataLoadingError.value = true
                users = emptyList()
            }
        }
    }

    fun getPeopleInDepartment(department: String, order: String) {
        viewModelScope.launch {
            val result = usersRepository.getUsersInDepartment(department, order)
            if (result is Result.Success) {
                isDataLoadingError.value = false
                users = result.data
            } else {
                isDataLoadingError.value = true
            }

        }
    }

    fun fieldSearch(search: String, sort: String) {
        viewModelScope.launch {
            val result = usersRepository.searchUsers(search)
            if (result is Result.Success) {
                users = result.data
                isDataLoadingError.value = false
            } else {
                isDataLoadingError.value = true
            }
            users.sortedBy {
                if (sort == UserSortingType.FirstName.sorting)
                    it.firstName
                else
                    it.birthday
            }
        }
    }
}