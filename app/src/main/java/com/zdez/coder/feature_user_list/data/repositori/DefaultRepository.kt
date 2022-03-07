package com.zdez.coder.feature_user_list.data.repositori

import com.zdez.coder.feature_user_list.data.data_source.database.UsersDao
import com.zdez.coder.feature_user_list.domain.repository.Repository

class DefaultRepository (val usersDao: UsersDao): Repository {
    //TODO
    override fun addUsers() {
        TODO("Not yet implemented")
    }

    override fun clearDatabase() {
        TODO("Not yet implemented")
    }
}