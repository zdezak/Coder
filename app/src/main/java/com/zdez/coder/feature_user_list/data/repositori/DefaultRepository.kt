package com.zdez.coder.feature_user_list.data.repositori

import com.zdez.coder.feature_user_list.data.data_source.database.UsersDao
import com.zdez.coder.feature_user_list.data.data_source.network.RemoteData
import com.zdez.coder.feature_user_list.domain.model.User
import com.zdez.coder.feature_user_list.domain.repository.Repository

class DefaultRepository(
    private val usersDao: UsersDao,
    private val remoteData: RemoteData,
) : Repository {

    override suspend fun addUsers(): List<User> {
        //TODO Добавить обработку ошибок
        val users = remoteData.getUser().body()!!.users
        usersDao.insertUsers(users)
        return users
    }

    override suspend fun clearDatabase() {
        usersDao.deleteAllUsers()
    }

    override suspend fun getUserById(id: String): User {
        return usersDao.getUserById(id)
    }

    override suspend fun getUsers(): List<User> {
        return usersDao.getAllUsers()
    }

    override suspend fun searchUsers(search: String): List<User> {
        val resultList = listOf<User>()
        resultList.plus(usersDao.getUsersWithSimilarFirstName(search))
        resultList.plus(usersDao.getUsersWithSimilarLastName(search))
        resultList.plus(usersDao.getUsersWithSimilarUserTag(search))
        return resultList
    }

    override suspend fun getUsersInDepartment(search: String): List<User> {
        return usersDao.getAllUsersInDepartment(search)
    }

    override suspend fun update(): List<User> {
        val users: List<User> = remoteData.getUser().body()!!.users
        usersDao.updateUsers(users)
        return users
    }
}