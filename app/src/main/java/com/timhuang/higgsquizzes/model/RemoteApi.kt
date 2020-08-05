package com.timhuang.higgsquizzes.model

import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by timhuang on 2020/8/4.
 **/
interface RemoteApi {

    companion object{
        private val baseUrl = "https://api.github.com"

        val client = OkHttpClient
            .Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

        val instance : RemoteApi by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(RemoteApi::class.java)
        }
    }

    @GET("/users")
    suspend fun getUsers(@Query("since")sinceId:Int, @Query("per_page")perPage:Int):List<User>

    @GET("/users/{userName}")
    suspend fun getUser(@Path("userName")userName:String): UserDetail

    @GET("/users/{userName}/followers")
    suspend fun getFollowers(@Path("userName")userName: String, @Query("page")page:Int,@Query("per_page") perPage:Int):List<User>

    @GET("/users/{userName}/following")
    suspend fun getFollowings(@Path("userName")userName: String, @Query("page")page:Int,@Query("per_page") perPage:Int):List<User>

}