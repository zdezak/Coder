package com.zdez.coder.data.source.remote

import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersDataSource

object UsersRemoteDataSource: UsersDataSource {
    override suspend fun getUsers(): Result<List<User>> {
        TODO("Not yet implemented")
    }
    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }
}