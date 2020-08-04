package com.timhuang.higgsquizzes.di

import com.timhuang.higgsquizzes.model.RemoteApi
import com.timhuang.higgsquizzes.model.UserRepository
import com.timhuang.higgsquizzes.model.UserRepositoryImpl

object DepencyProvider {

    fun getUserRepository():UserRepository{
        return UserRepositoryImpl(RemoteApi.instance)
    }
}