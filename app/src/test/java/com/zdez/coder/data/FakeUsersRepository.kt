package com.zdez.coder.data

import com.zdez.coder.data.source.UsersRepository
import java.lang.Exception
import java.util.LinkedHashMap

class FakeUsersRepository : UsersRepository {

    var usersServiceData: LinkedHashMap<String, User> = LinkedHashMap()
    private var shouldReturnError = false

    override suspend fun getUsers(order: String): Result<List<User>> {
        if (shouldReturnError){
            return Result.Error(Exception("Test exception"))
        }else{
            return Result.Success(usersServiceData.values.toList())
        }
    }

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
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


    override suspend fun searchUsers(search: String): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }

}