package com.zdez.coder.feature_user_list.data.repository

import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.model.UsersData
import com.zdez.coder.feature_user_list.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserRepository : Repository {

    private var users = mutableListOf<User>()

    override suspend fun addUsers(): UsersData {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUsers(search: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersInDepartment(search: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun update(): List<User> {
        TODO("Not yet implemented")
    }
}