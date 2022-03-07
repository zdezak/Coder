package com.zdez.coder.feature_user_list.presentation.profile

import androidx.lifecycle.ViewModel
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun getUsers() {

    }

    fun updateUsers() {

    }
}