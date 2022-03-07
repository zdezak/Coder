package com.zdez.coder.feature_user_list.data.data_source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zdez.coder.feature_user_list.domain.model.User

@Dao
interface UsersDao {

    @Insert
    suspend fun insertPeople(users: List<User>)

    @Update
    suspend fun update(users: List<User>)

    @Query("SELECT * FROM users ORDER BY :order DESC")
    suspend fun getAllPeople(order: String): List<User>

    @Query("SELECT * FROM users WHERE department = :key ORDER BY :order DESC")
    suspend fun getAllPeopleInDepartment(key: String, order: String): List<User>

    @Query("SELECT * FROM users WHERE firstName LIKE :search")
    suspend fun getPeopleWithSimilarFirstName(search: String): List<User>

    @Query("SELECT * FROM users WHERE lastName LIKE :search")
    suspend fun getPeopleWithSimilarLastName(search: String): List<User>

    @Query("SELECT * FROM users WHERE userTag LIKE :search")
    suspend fun getPeopleWithSimilarUserTag(search: String): List<User>

    @Query("DELETE FROM users")
    suspend fun clear()

}