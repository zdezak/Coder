package com.zdez.coder.feature_user_list.data.repositori

import com.zdez.coder.feature_user_list.data.data_source.database.UsersDao
import com.zdez.coder.feature_user_list.data.data_source.network.RemoteData
import com.zdez.coder.feature_user_list.domain.repository.Repository

class DefaultRepository(
    private val usersDao: UsersDao,
    private val remoteData: RemoteData,
) : Repository {

    override suspend fun addUsers() {
        //TODO Добавить обработку ошибок
        val users = remoteData.getUser().body()!!.users
        usersDao.insertPeople(users)
    }

    override suspend fun clearDatabase() {
        usersDao.clear()
    }
}