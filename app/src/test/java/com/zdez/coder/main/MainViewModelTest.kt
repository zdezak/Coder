package com.zdez.coder.main

import com.zdez.coder.data.FakeUsersRepository
import com.zdez.coder.data.User
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MainViewModelTest : TestCase() {
    private lateinit var viewModel: MainViewModel
    private lateinit var userRepository: FakeUsersRepository

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
        viewModel = MainViewModel(userRepository)
    }

    @Test
    fun getUsers() {

    }


}