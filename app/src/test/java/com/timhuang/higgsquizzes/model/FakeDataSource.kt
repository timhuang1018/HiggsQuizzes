package com.timhuang.higgsquizzes.model

import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail

/**
 * Created by timhuang on 2020/8/4.
 **/

class FakeDataSource(private val users:List<User>,private val user:UserDetail) :RemoteApi{

    override suspend fun getUsers(sinceId: Int, perPage: Int): List<User> {
        return users
    }

    override suspend fun getUser(userName: String): UserDetail {
        return user
    }

    override suspend fun getFollowers(userName: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getFollowings(userName: String): List<User> {
        TODO("Not yet implemented")
    }

}