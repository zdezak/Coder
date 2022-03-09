package com.zdez.coder.feature_user_list.domain.repository

import com.zdez.coder.feature_user_list.domain.model.User

interface Repository {

    suspend fun addUsers(): List<User>

    suspend fun clearDatabase()

    fun getUserById(id: String): User

    suspend fun getUsers(): List<User>

    suspend fun searchUsers(search: String): List<User>

    suspend fun getUsersInDepartment(search: String): List<User>

    suspend fun update(): List<User>

}