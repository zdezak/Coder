package com.zdez.coder.data.source

import com.zdez.coder.data.Result
import com.zdez.coder.data.User

interface UsersDataSource {

    suspend fun getUsers(): Result<List<User>>

    suspend fun saveUsers(users: List<User>)

    suspend fun deleteAllUsers()

    suspend fun searchUsers(search: String): List<User>

    suspend fun getUsersInDepartment(department: String, order: String): List<User>
}