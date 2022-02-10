package com.zdez.coder.data.source

import com.zdez.coder.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.zdez.coder.data.Result

class DefaultUsersRepository(
    private val usersRemoteDataSource: UsersDataSource,
    private val usersLocalDataSource: UsersDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {
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