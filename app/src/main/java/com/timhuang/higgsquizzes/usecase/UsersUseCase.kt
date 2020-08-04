package com.timhuang.higgsquizzes.usecase

import androidx.lifecycle.MutableLiveData
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.helper.RequestState
import com.timhuang.higgsquizzes.helper.Result
import com.timhuang.higgsquizzes.model.UserRepository

/**
 * Created by timhuang on 2020/8/4.
 **/

class UsersUseCase (private val repository: UserRepository) {

    val users = MutableLiveData<List<User>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<EventWrapper<String>>()

    suspend fun getUsers() {
        isLoading.value = true

        val result = repository.getUsers()
        if (result is Result.Success){
            users.value = users.value?.let { list -> list + result.data } ?: result.data
        }else{
            //simply notify user
            error.value = EventWrapper("fetch data fail")
        }
        isLoading.value = false
    }
}