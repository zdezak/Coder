package com.zdez.coder.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.coder.data.source.UsersRepository

class PeopleViewModelFactory(
    private val usersRepository: UsersRepository,
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleViewModel::class.java)) {
            return PeopleViewModel(usersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}