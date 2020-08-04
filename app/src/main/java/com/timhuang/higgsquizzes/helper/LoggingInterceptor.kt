package com.timhuang.higgsquizzes.helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by timhuang on 2020/8/4.
 **/

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("LoggingInterceptor","request url :${request.url()} ,body:${request.body()}")

        return chain.proceed(request)
    }
}
