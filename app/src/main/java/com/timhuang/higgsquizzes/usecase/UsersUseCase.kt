package com.timhuang.higgsquizzes.usecase

import androidx.lifecycle.MutableLiveData
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.helper.Result
import com.timhuang.higgsquizzes.model.UserRepository

/**
 * Created by timhuang on 2020/8/4.
 **/

class UsersUseCase (private val repository: UserRepository) {

    val users = MutableLiveData<List<User>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<EventWrapper<String>>()
    //sinceId: The integer ID of the last User that you've seen.
    private var sinceId = 0
    private val perPage = 20

    suspend fun getUsers():Boolean {
        isLoading.value = true
        var moreData = true
        val result = repository.getUsers(sinceId,perPage)
        if (result is Result.Success){
            users.value = users.value?.let { list -> list + result.data } ?: result.data

            result.data.lastOrNull()?.let { sinceId = it.id }
            //check if no more data
            if(result.data.size<perPage){
                moreData = false
            }
        }else{
            //simply notify user
            error.value = EventWrapper("fetch data fail")
        }
        isLoading.value = false
        return moreData
    }
}