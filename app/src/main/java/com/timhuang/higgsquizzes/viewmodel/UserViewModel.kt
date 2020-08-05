package com.timhuang.higgsquizzes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.di.DepencyProvider
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.usecase.UserUseCase

/**
 * Created by timhuang on 2020/8/4.
 * skip using constructor injection with useCase to simply generating viewModel (without factory class)
 **/
class UserViewModel :ViewModel(){

    private val useCase : UserUseCase = UserUseCase(scope = viewModelScope,repository = DepencyProvider.getUserRepository())

    val isLoading :LiveData<Boolean>
        get() = useCase.isLoading

    val error :LiveData<EventWrapper<String>>
        get() = useCase.error


    fun getUser(userName:String) :LiveData<UserDetail> {
        return useCase.getUser(userName)
    }

    fun biFollows(userName:String):LiveData<List<User>>{
        return useCase.biFollows(userName)
    }

    override fun onCleared() {
        super.onCleared()
        useCase.cancel()
    }
}