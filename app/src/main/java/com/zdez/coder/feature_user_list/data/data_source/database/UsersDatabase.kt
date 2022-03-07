package com.zdez.coder.feature_user_list.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zdez.coder.feature_user_list.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}
