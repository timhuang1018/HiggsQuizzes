package com.timhuang.higgsquizzes.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.helper.Result
import com.timhuang.higgsquizzes.model.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2020/8/4.
 **/

class UserUseCase (private val scope:CoroutineScope,private val repository: UserRepository) {

    val user = MutableLiveData<UserDetail>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<EventWrapper<String>>()

    fun getUser(userName:String): LiveData<UserDetail> {
        scope.launch {
            isLoading.value = true
            val result = repository.getUser(userName)
            if (result is Result.Success){
                user.value = result.data
            }else{
                //simply notify user
                error.value = EventWrapper("fetch data fail")
            }
            isLoading.value = false
        }
        return user
    }
}