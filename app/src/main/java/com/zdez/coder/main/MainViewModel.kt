package com.zdez.coder.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.ApiCoder
import com.zdez.coder.data.PeopleDao
import com.zdez.coder.data.User
import com.zdez.coder.data.Users
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val dataSource: PeopleDao) : ViewModel() {
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
    var people by mutableStateOf(listOf<User>())
        private set
    val isSuccessed = mutableStateOf<Boolean?>(null)

    init {
        clearDatabase()
        getPeople()
        saveInDatabase()
    }

    fun getPeople() {

        ApiCoder.retrofitService.getUsers()
            .enqueue(object : Callback<Users> {
                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>,
                ) {
                    people = response.body()!!.items
                    saveInDatabase()
                    isSuccessed.value = true
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    isSuccessed.value = false
                }
            })
    }

    private fun saveInDatabase() {

        viewModelScope.launch {
            dataSource.insertPeople(people)
        }
    }

    private fun clearDatabase() {
        viewModelScope.launch { dataSource.clear() }
    }
}