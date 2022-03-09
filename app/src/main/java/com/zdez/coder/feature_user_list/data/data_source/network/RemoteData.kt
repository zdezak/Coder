package com.zdez.coder.feature_user_list.data.data_source.network

import com.zdez.coder.feature_user_list.domain.model.NetworkResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiService: ApiService) {

    fun getUser(): Response<NetworkResponse> = apiService.getUsers()

}