package com.timhuang.higgsquizzes.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.timhuang.higgsquizzes.data.FakeData
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val testDispatcher : TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var remoteApi :RemoteApi
    private lateinit var repository: UserRepository

    private val users = FakeData.users
    private val user = FakeData.user


    @Before
    fun setupRepo(){
        remoteApi = FakeDataSource(users = users,user = user)
        repository = UserRepositoryImpl(remoteApi)
    }

    @Before
    fun setupDispatcher(){
        Dispatchers.setMain(testDispatcher)
    }
    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher(){
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun getUsers_success() = runBlockingTest{
        val result = repository.getUsers()
        assertThat(result,`is`(Result::class.java))
        if (result is Result.Success){
            assertThat(result.data, `is`(notNullValue()))
        }
    }

    @Test
    fun getUserDetail_success() = runBlockingTest{
        val result = repository.getUser(user.name)
        assertThat(result,`is`(Result::class.java))
        if (result is Result.Success){
            assertThat(result.data.name, `is`("Tim"))
        }
    }
}