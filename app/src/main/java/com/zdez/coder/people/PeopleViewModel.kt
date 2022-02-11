package com.zdez.coder.people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.data.User
import com.zdez.coder.data.source.local.UsersDao
import kotlinx.coroutines.launch

class PeopleViewModel(val dataSource: UsersDao) : ViewModel() {
    val tabs = Tabs().listTabs

    var people by mutableStateOf(listOf<User>())
        private set

    init {
        getPeople(UserFilterType.FirstName.filter)
    }

    fun getPeople(order: String) {
        viewModelScope.launch {
            people = dataSource.getUsers(order)
        }
    }

    fun getPeopleInDepartment(department: String, order: String) {
        viewModelScope.launch {
            people = dataSource.getAllPeopleInDepartment(department, order)
        }
    }

    fun fieldSearch(search: String, sort: String) {
        viewModelScope.launch {
            people = dataSource.getPeopleWithSimilarFirstName(search)
            people.plus(dataSource.getPeopleWithSimilarLastName(search))
            people.plus(dataSource.getPeopleWithSimilarUserTag(search))
            people.sortedBy {
                if (sort == UserFilterType.FirstName.filter)
                    it.firstName
                else
                    it.birthday
            }
        }
    }
}