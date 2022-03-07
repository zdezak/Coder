package com.zdez.coder.data.source.remote

import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import com.zdez.coder.data.Users
import com.zdez.coder.data.source.UsersDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object UsersRemoteDataSource : UsersDataSource {
    override suspend fun getUsers(): Result<List<User>> {
        var userResult: Result<List<User>> = Result.Loading
        return suspendCoroutine {resultCoroutine->
            ApiCoder.retrofitService.getUsers()
                .enqueue(object : Callback<Users> {
                    override fun onResponse(
                        call: Call<Users>,
                        response: Response<Users>,
                    ) {
                        if (response.isSuccessful)
                            userResult = Result.Success(response.body()!!.items)
                    }

                    override fun onFailure(call: Call<Users>, t: Throwable) {
                        userResult = Result.Error(Exception(t.message))
                    }
                })

            //return don't work
            resultCoroutine.resume(userResult)
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun searchUsers(search: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersInDepartment(department: String, order: String): List<User> {
        TODO("Not yet implemented")
    }
}