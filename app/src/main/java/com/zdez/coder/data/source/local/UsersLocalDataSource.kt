package com.zdez.coder.data.source.local

import com.zdez.coder.data.Result
import com.zdez.coder.data.Result.*
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersLocalDataSource internal constructor(
    private val userDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersDataSource {

    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(userDao.getUsers())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.insertPeople(users)
    }

    override suspend fun deleteAllUsers() {
        userDao.clear()
    }
}