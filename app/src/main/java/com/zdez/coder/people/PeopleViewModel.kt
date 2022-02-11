package com.zdez.coder.people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.R
import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import com.zdez.coder.data.source.local.UsersDao
import kotlinx.coroutines.launch

class PeopleViewModel(val dataSource: UsersDao) : ViewModel() {
    private var currentFiltering = TasksFilterType.ALL_TASKS
    val tabs = Tabs().listTabs

    var people by mutableStateOf(listOf<User>())
        private set

    init {
        getPeople(UserSortingType.FirstName.sorting)
    }

    fun getPeople(order: String) {
        viewModelScope.launch {
            people = dataSource.getUsers(order)
        }
    }

    fun getUsers(usersResult: Result<List<User>>) {
        val result = MutableLiveData<List<User>>()
        viewModelScope.launch {
            if (usersResult is Result.Success) {
                isDataLoadingError.value = false
                viewModelScope.launch {
                    result.value = filterItems(usersResult.data, currentFiltering)
                }
            } else {
                result.value = emptyList()
                showSnackbarMessage(R.string.loading_tasks_error)
                isDataLoadingError.value = true
            }
            return result
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
                if (sort == UserSortingType.FirstName.sorting)
                    it.firstName
                else
                    it.birthday
            }
        }
    }
}