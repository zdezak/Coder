package com.zdez.coder.di

import android.app.Application
import androidx.room.Room
import com.zdez.coder.feature_user_list.data.data_source.database.UsersDatabase
import com.zdez.coder.feature_user_list.data.repositori.DefaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): UsersDatabase {
        return Room.databaseBuilder(
            app,
            UsersDatabase::class.java,
            UsersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: UsersDatabase): DefaultRepository {
        return DefaultRepository(db.usersDao)
    }

/*    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }*/
}