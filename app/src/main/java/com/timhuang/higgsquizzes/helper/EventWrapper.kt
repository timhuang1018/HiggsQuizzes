package com.timhuang.higgsquizzes.helper

/**
 * Created by timhuang on 2020/8/4.
 **/

class EventWrapper<T>(private val content:T) {
    private var isUsed = false
    fun contentGetHandled():T?{
        if (isUsed){
            return null
        }else{
            isUsed = true
            return content
        }
    }
}