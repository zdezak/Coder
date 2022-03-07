package com.zdez.coder.data.source.local

import com.zdez.coder.data.Result
import com.zdez.coder.data.Result.Error
import com.zdez.coder.data.Result.Success
import com.zdez.coder.data.User
import com.zdez.coder.data.source.UsersDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersLocalDataSource internal constructor(
    private val userDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
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

    override suspend fun searchUsers(search: String): List<User> {
        val result = listOf<User>()
        result.plus(userDao.getPeopleWithSimilarFirstName(search))
        result.plus(userDao.getPeopleWithSimilarLastName(search))
        result.plus(userDao.getPeopleWithSimilarUserTag(search))
        return result
    }

    override suspend fun getUsersInDepartment(department: String, order: String): List<User> {
        return userDao.getAllPeopleInDepartment(department,order)
    }

    override suspend fun deleteAllUsers() {
        userDao.clear()
    }
}