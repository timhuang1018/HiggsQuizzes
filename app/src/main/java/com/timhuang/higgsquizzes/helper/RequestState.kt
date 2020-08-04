package com.timhuang.higgsquizzes.helper

import androidx.lifecycle.MutableLiveData

/**
 * Created by timhuang on 2020/8/4.
 * let UI bind each to display data or info
 **/

data class RequestState<T>(val data: MutableLiveData<T>,
                           val isLoading: MutableLiveData<Boolean>,
                           val error : MutableLiveData<EventWrapper<String>>)

sealed class Result<out R> {
    data class Success<out T>(val data:T):Result<T>()
    object Failure:Result<Nothing>()
}

