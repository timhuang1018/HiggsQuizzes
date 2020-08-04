package com.timhuang.higgsquizzes.model

import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.RequestState
import com.timhuang.higgsquizzes.helper.Result

/**
 * Created by timhuang on 2020/8/4.
 * repository is processing data, and return result ( success or fail )
 * simply error handling by just return failure
 **/

class UserRepositoryImpl(private val remoteApi: RemoteApi) :UserRepository{

    //ignore paging now
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val result = remoteApi.getUsers(0,20)
            Result.Success(result)
        }catch (e:Exception){
            Result.Failure
        }
    }

    override suspend fun getUser(userName: String): Result<UserDetail> {
        return try {
            val result = remoteApi.getUser(userName)
            Result.Success(result)
        }catch (e:Exception){
            Result.Failure
        }
    }

    override suspend fun getFollowers(userName: String): Result<List<User>> {
        return try {
            val result = remoteApi.getFollowers(userName)
            Result.Success(result)
        }catch (e:Exception){
            Result.Failure
        }
    }

    override suspend fun getFollowings(userName: String): Result<List<User>> {
        return try {
            val result = remoteApi.getFollowings(userName)
            Result.Success(result)
        }catch (e:Exception){
            Result.Failure
        }
    }


}