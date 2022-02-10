package com.zdez.coder.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.User
import com.zdez.coder.data.Users
import com.zdez.coder.data.source.UsersRepository
import com.zdez.coder.data.source.remote.ApiCoder
import com.zdez.coder.data.succeeded
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    val tabs = listOf(
        "all",
        "android",
        "ios",
        "design",
        "management",
        "qa",
        "back_office",
        "frontend",
        "hr",
        "pr",
        "backend",
        "support",
        "analytics"
    )
    var users by mutableStateOf(listOf<User>())
        private set
    val isSuccessed = mutableStateOf<Boolean?>(null)

    init {
        clearDatabase()
        getUsers()
        saveInDatabase()
    }

    fun getUsers() {
        viewModelScope.launch {
            users = usersRepository.getUsers()
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