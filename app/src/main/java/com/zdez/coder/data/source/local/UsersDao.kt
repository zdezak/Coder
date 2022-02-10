package com.zdez.coder.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zdez.coder.data.User

@Dao
interface UsersDao {

    @Insert
    suspend fun insertPeople(people: List<User>)

    @Update
    suspend fun update(people: List<User>)

    @Query("SELECT * FROM people ORDER BY :order DESC")
    suspend fun getUsers(order: String = "firstName"): List<User>

    @Query("SELECT * FROM people WHERE department = :key ORDER BY :order DESC")
    suspend fun getAllPeopleInDepartment(key: String, order: String): List<User>

    @Query("SELECT * FROM people WHERE firstName LIKE :search")
    suspend fun getPeopleWithSimilarFirstName(search: String): List<User>

    @Query("SELECT * FROM people WHERE lastName LIKE :search")
    suspend fun getPeopleWithSimilarLastName(search: String): List<User>

    @Query("SELECT * FROM people WHERE userTag LIKE :search")
    suspend fun getPeopleWithSimilarUserTag(search: String): List<User>

    @Query("DELETE FROM people")
    suspend fun clear()

}