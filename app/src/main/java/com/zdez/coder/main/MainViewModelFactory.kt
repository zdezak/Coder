package com.zdez.coder.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.coder.data.source.local.UsersDao

class MainViewModelFactory(
    private val dataSource: UsersDao,
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}