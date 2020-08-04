package com.timhuang.higgsquizzes.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.timhuang.higgsquizzes.model.FakeTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersUseCaseTest{

    val testDispatcher : TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExcutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher(){
        Dispatchers.setMain(testDispatcher)
    }
    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    private lateinit var repository: FakeTestRepository
    private lateinit var usersUseCase: UsersUseCase

    @Before
    fun setupViewModel(){
        repository = FakeTestRepository()
        usersUseCase = UsersUseCase(repository)
    }

    @Test
    fun fetchUsersSuccess_andStopIsLoading() = runBlockingTest{
        //WHEN
        usersUseCase.getUsers()

        //THEN
        assertThat(usersUseCase.users.value,`is`(notNullValue()))
        assertThat(usersUseCase.error.value, `is`(nullValue()))
        assertThat(usersUseCase.isLoading.value, `is`(false))
    }

    @Test
    fun fetchUsersFail_andStopIsLoading() = runBlockingTest{
        //GIVEN fetch data will fail
        repository.makeApiFail()

        //WHEN
        usersUseCase.getUsers()

        //THEN
        assertThat(usersUseCase.users.value,`is`(nullValue()))
        assertThat(usersUseCase.error.value, `is`(notNullValue()))
        assertThat(usersUseCase.isLoading.value, `is`(false))
    }
}