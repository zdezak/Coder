package com.zdez.coder.feature_user_list.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    lateinit var user: User

    fun getUser(id: String) {
        viewModelScope.launch {
            user = repository.getUserById(id)
        }

    }
}