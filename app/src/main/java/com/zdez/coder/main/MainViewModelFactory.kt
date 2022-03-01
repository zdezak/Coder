package com.zdez.coder.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.coder.data.source.UsersRepository


class MainViewModelFactory(
    private val usersRepository: UsersRepository
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(usersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}