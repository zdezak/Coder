package com.zdez.coder.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.ApiCoder
import com.zdez.coder.data.People
import com.zdez.coder.data.PeopleDao
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
    var people by mutableStateOf(listOf<People>())
        private set
    val isFailed = mutableStateOf(false)

    init {
        viewModelScope.launch {
            getPeople()
            if (!isFailed.value) {
                dataSource.insertPeople(people)
            }
        }
    }

    fun getPeople() {
        isFailed.value = true
        ApiCoder.retrofitService.getUsers()
            .enqueue(object : Callback<List<People>> {
                override fun onResponse(
                    call: Call<List<People>>,
                    response: Response<List<People>>,
                ) {
                    if (response.code() == 200) {
                        people = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<People>>, t: Throwable) {
                    isFailed.value = false
                }
            })
    }
}