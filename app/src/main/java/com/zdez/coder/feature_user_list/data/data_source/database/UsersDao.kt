package com.zdez.coder.feature_user_list.data.data_source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zdez.coder.feature_user_list.domain.model.User

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUsers(users: List<User>)

    @Update
    suspend fun updateUsers(users: List<User>)

    @Query("SELECT * FROM users ORDER BY :order DESC")
    suspend fun getAllUsers(order: String): List<User>

    @Query("SELECT * FROM users WHERE department = :key ORDER BY :order DESC")
    suspend fun getAllUsersInDepartment(key: String, order: String): List<User>

    @Query("SELECT * FROM users WHERE firstName LIKE :search")
    suspend fun getUsersWithSimilarFirstName(search: String): List<User>

    @Query("SELECT * FROM users WHERE lastName LIKE :search")
    suspend fun getUsersWithSimilarLastName(search: String): List<User>

    @Query("SELECT * FROM users WHERE userTag LIKE :search")
    suspend fun getUsersWithSimilarUserTag(search: String): List<User>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: String): User

}