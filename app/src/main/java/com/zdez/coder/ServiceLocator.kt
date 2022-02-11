package com.zdez.coder

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.zdez.coder.data.source.DefaultUsersRepository
import com.zdez.coder.data.source.UsersDataSource
import com.zdez.coder.data.source.UsersRepository
import com.zdez.coder.data.source.local.UserDatabase
import com.zdez.coder.data.source.local.UsersLocalDataSource
import com.zdez.coder.data.source.remote.UsersRemoteDataSource
import kotlinx.coroutines.runBlocking

object ServiceLocator {

    private val lock = Any()
    private var database: UserDatabase? = null
    @Volatile
    var tasksRepository: UsersRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): UsersRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): UsersRepository {
        val newRepo = DefaultUsersRepository(UsersRemoteDataSource, createTaskLocalDataSource(context))
        tasksRepository = newRepo
        return newRepo
    }

    private fun createTaskLocalDataSource(context: Context): UsersDataSource {
        val database = database ?: createDataBase(context)
        return UsersLocalDataSource(database.usersDao)
    }

    private fun createDataBase(context: Context): UserDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                UsersRemoteDataSource.deleteAllUsers()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            tasksRepository = null
        }
    }
}