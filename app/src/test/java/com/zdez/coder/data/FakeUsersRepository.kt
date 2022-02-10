package com.zdez.coder.data

import com.zdez.coder.data.source.UsersRepository
import kotlin.Result

class FakeUsersRepository : UsersRepository {
    override suspend fun getUsers(order: String): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersInDepartment(
        department: String,
        order: String
    ): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override fun insertUsers(users: List<User>) {
        TODO("Not yet implemented")
    }


    override suspend fun searchUsers(search: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }

}