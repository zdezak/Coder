package com.zdez.coder.feature_user_list.domain.repository

import com.zdez.coder.feature_user_list.domain.model.User

interface Repository {

    suspend fun addUsers()

    suspend fun clearDatabase()
}