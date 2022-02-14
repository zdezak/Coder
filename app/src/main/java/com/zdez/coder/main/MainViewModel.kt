package com.zdez.coder.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.Result
import com.zdez.coder.data.Result.Success
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersRepository
import com.zdez.coder.people.Tabs
import kotlinx.coroutines.launch

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    val tabs = Tabs().listTabs
    val isDataLoadingError = mutableStateOf<Boolean?>(null)

    init {
        downloadUsers()
    }

    fun downloadUsers() {
        viewModelScope.launch {
            saveUsers(usersRepository.getUsers())
        }
    }

    private fun saveUsers(usersResult: Result<List<User>>) {
        viewModelScope.launch {
            if (usersResult is Success) {
                isDataLoadingError.value = false
                viewModelScope.launch {
                    saveInDatabase(usersResult.data)
                }
            } else {
                isDataLoadingError.value = true
            }
        }
    }

    private fun saveInDatabase(result: List<User>) {

        viewModelScope.launch {
            usersRepository.insertUsers(result)
        }
    }
}