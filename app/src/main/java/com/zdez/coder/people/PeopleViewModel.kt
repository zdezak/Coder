package com.zdez.coder.people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.People
import com.zdez.coder.data.PeopleDao
import kotlinx.coroutines.launch

class PeopleViewModel(val dataSource: PeopleDao) : ViewModel() {
    val tabs = listOf<String>(
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

    init {
        getPeople("firstName")
    }

    fun getPeople(order: String) {
        viewModelScope.launch {
            dataSource.getAllPeople(order)
        }
    }

    fun getPeopleInDepartment(department: String, order: String) {
        viewModelScope.launch {
            dataSource.getAllPeopleInDepartment(department, order)
        }
    }
}