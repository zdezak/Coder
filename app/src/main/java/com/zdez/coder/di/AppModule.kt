package com.zdez.coder.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zdez.coder.feature_user_list.data.data_source.database.UsersDatabase
import com.zdez.coder.feature_user_list.data.data_source.network.ApiService
import com.zdez.coder.feature_user_list.data.data_source.network.RemoteData
import com.zdez.coder.feature_user_list.data.repositori.DefaultRepository
import com.zdez.coder.feature_user_list.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideRepository(db: UsersDatabase, remoteData: RemoteData): Repository {
        return DefaultRepository(db.usersDao, remoteData)
    }

    @Provides
    fun provideBaseUrl(): String =
        "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteData(apiService: ApiService): RemoteData = RemoteData(apiService)

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