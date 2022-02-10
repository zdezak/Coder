package com.zdez.coder.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zdez.coder.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}