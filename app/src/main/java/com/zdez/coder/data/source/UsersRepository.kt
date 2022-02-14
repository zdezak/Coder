package com.zdez.coder.data.source

import com.zdez.coder.data.User
import com.zdez.coder.data.Result

interface UsersRepository {

    suspend fun getUsers(order: String = "firstName"): Result<List<User>>

    suspend fun getUsersInDepartment(department: String, order: String): Result<List<User>>

    fun insertUsers(users: List<User>)

    suspend fun searchUsers(search: String):  Result<List<User>>

    suspend fun clearDatabase()
}