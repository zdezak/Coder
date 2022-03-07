package com.zdez.coder.data.source

import com.zdez.coder.data.Result
import com.zdez.coder.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultUsersRepository(
    private val usersRemoteDataSource: UsersDataSource,
    private val usersLocalDataSource: UsersDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {
    override suspend fun getUsers(order: String): Result<List<User>> {
        clearDatabase()
        val result = usersRemoteDataSource.getUsers()
        if (result is Result.Success) {
            usersLocalDataSource.saveUsers(result.data)
        }
        return usersLocalDataSource.getUsers()
    }

    override suspend fun getUsersInDepartment(
        department: String,
        order: String
    ): Result<List<User>> {
        return Result.Success(usersLocalDataSource.getUsersInDepartment(department, order))
    }

    override suspend fun insertUsers(users: List<User>) {
        usersLocalDataSource.saveUsers(users)
    }


    override suspend fun searchUsers(search: String): Result<List<User>> {
        return Result.Success(usersLocalDataSource.searchUsers(search))
    }

    override suspend fun clearDatabase() {
        usersLocalDataSource.deleteAllUsers()
    }

}