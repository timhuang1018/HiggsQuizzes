package com.timhuang.higgsquizzes.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by timhuang on 2020/8/4.
 **/

@ExperimentalCoroutinesApi
class RemoteApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val testDispatcher : TestCoroutineDispatcher = TestCoroutineDispatcher()
    val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private lateinit var mockWebServer: MockWebServer
    private lateinit var remoteApi :RemoteApi

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


    @Before
    fun createService(){
        mockWebServer = MockWebServer()
        remoteApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(RemoteApi::class.java)
    }

    @After
    fun close(){
        mockWebServer.shutdown()
    }

    @Test
    fun getUsers_returnFirstPage() {
        enqueueResponse("users.json")

        val items = runBlocking {
            remoteApi.getUsers(0,20)
        }
        assertThat(items.size,`is`(2))
        assertThat(items.first().id,`is`(1))
        assertThat(items.first().login,`is`("octocat"))
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }


}