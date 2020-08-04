package com.timhuang.higgsquizzes.model

import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.RequestState
import com.timhuang.higgsquizzes.helper.Result

/**
 * Created by timhuang on 2020/8/4.
 **/

interface UserRepository {

    suspend fun getUsers():Result<List<User>>
    suspend fun getUser(userName: String):Result<UserDetail>
    suspend fun getFollowers(userName: String):Result<List<User>>
    suspend fun getFollowings(userName: String):Result<List<User>>

}