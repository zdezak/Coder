package com.zdez.coder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class PeopleDatabase : RoomDatabase() {
    abstract val peopleDao: PeopleDao

    companion object {
        @Volatile
        private var INSTANCE: PeopleDatabase? = null
        fun getInstance(context: Context): PeopleDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PeopleDatabase::class.java,
                        "people"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}