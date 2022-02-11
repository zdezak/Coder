package com.zdez.coder.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.R
import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersRepository
import com.zdez.coder.people.Tabs
import kotlinx.coroutines.launch

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    val tabs = Tabs().listTabs
    private var users by mutableStateOf(listOf<User>())
        private set
    val isDataLoadingError = mutableStateOf<Boolean?>(null)

    init {
        clearDatabase()
        getUsers()
        saveInDatabase()
    }

    fun getUsers(usersResult: Result<List<User>>) {
        val result = MutableLiveData<List<User>>()
        viewModelScope.launch {
            if (usersResult is Result.Success) {
                isDataLoadingError.value = false
                viewModelScope.launch {
                    result.value = filterItems(usersResult.data)
                }
            } else {
                result.value = emptyList()
                showSnackbarMessage(R.string.loading_tasks_error)
                isDataLoadingError.value = true
            }
            return result
        }
    }

    private fun saveInDatabase() {

        viewModelScope.launch {
            usersRepository.insertUsers(users)
        }
    }

    private fun clearDatabase() {

        viewModelScope.launch {
            usersRepository.clearDatabase()
        }
    }
}