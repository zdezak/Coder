package com.zdez.coder.feature_user_list.presentation.loading

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.coder.feature_user_list.domain.model.UsersData
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var users = mutableStateOf(UsersData(loading = true, data = listOf(), error = null))
    fun loadingUsers() {
        viewModelScope.launch {
            users.value = repository.addUsers()
        }
    }

}
