package com.zdez.coder.feature_user_list.data.data_source.network

import com.zdez.coder.feature_user_list.domain.model.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<NetworkResponse>
}
