package com.zdez.coder.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiServiceCoder {
    @GET("users")
    fun getUsers(): Call<People>
}

object ApiCoder {

    val retrofitService: ApiServiceCoder by lazy {
        retrofit.create(ApiServiceCoder::class.java)
    }

}