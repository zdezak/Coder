package com.zdez.coder.main

import com.zdez.coder.MainCoroutineRule
import com.zdez.coder.data.FakeUsersRepository
import com.zdez.coder.data.User
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : TestCase() {
    private lateinit var viewModel: MainViewModel
    private lateinit var userRepository: FakeUsersRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUpViewModel() {
        userRepository = FakeUsersRepository()
        val users = listOf(
            User(
                "0",
                "https:/temp.ru/0",
                "Andrey",
                "Smith",
                "as",
                "ios",
                "Senior",
                "2020:01:14",
                "+79009009009"
            ),
            User(
                "1",
                "https:/temp.ru/1",
                "Michel",
                "Smith",
                "ms",
                "android",
                "Junior",
                "2020:01:14",
                "+79009009009"
            )
        )
        userRepository.insertUsers(users)
        viewModel = MainViewModel(userRepository, mainCoroutineRule.dispatcher)
    }

    @Test
    fun getUsersSuccess() {
        viewModel.downloadUsers()
        assertEquals(viewModel.isDataLoadingError,false)
    }

    @Test
    fun getUsersError(){
        viewModel.downloadUsers()
        userRepository.setReturnError(true)
        assertEquals(viewModel.isDataLoadingError,true)
    }

}