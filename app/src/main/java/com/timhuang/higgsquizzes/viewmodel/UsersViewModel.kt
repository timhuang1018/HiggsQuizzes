package com.timhuang.higgsquizzes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.di.DepencyProvider
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.usecase.UsersUseCase
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2020/8/4.
 * skip using constructor injection with useCase to simply generating viewModel (without factory class)
 **/

class UsersViewModel: ViewModel() {

    private val useCase : UsersUseCase = UsersUseCase(repository = DepencyProvider.getUserRepository())

    var moreData = false
    private set

    val users:LiveData<List<User>>
    get() = useCase.users

    val isLoading :LiveData<Boolean>
    get() = useCase.isLoading

    val error :LiveData<EventWrapper<String>>
    get() = useCase.error

    fun getUsers(){
        viewModelScope.launch {
            moreData = useCase.getUsers()
        }
    }

    fun isLoading():Boolean{
        return isLoading.value==true
    }

}