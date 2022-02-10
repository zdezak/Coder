package com.zdez.coder.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.coder.data.source.local.UsersDao

class PeopleViewModelFactory(
    private val dataSource: UsersDao,
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleViewModel::class.java)) {
            return PeopleViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}