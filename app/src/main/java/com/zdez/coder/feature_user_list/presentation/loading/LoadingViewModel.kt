package com.zdez.coder.feature_user_list.presentation.loading

import androidx.lifecycle.ViewModel
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun loadingUsers() {

    }
}