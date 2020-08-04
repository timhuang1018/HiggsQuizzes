package com.timhuang.higgsquizzes.model

import com.timhuang.higgsquizzes.data.FakeData
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.Result

/**
 * Created by timhuang on 2020/8/4.
 **/

class FakeTestRepository : UserRepository {
    var shouldThrow = false

    fun makeApiFail(){
        shouldThrow = true
    }

    override suspend fun getUsers(since:Int,perPage:Int): Result<List<User>> {
        if (shouldThrow){
            return Result.Failure
        }
        return Result.Success(FakeData.users)
    }

    override suspend fun getUser(userName: String): Result<UserDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun getFollowers(userName: String): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFollowings(userName: String): Result<List<User>> {
        TODO("Not yet implemented")
    }
}