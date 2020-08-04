package com.timhuang.higgsquizzes.data

import com.squareup.moshi.Json
import com.timhuang.higgsquizzes.adapter.AdapterClick
import com.timhuang.higgsquizzes.adapter.RecyclerItem

/**
 * Created by timhuang on 2020/8/3.
 **/

//for users page
data class User (
    @field:Json(name = "id") override val id:Int,
    @field:Json(name = "avatar_url") val headPic :String,
    @field:Json(name = "login") val login:String,
    @field:Json(name="site_admin") val isAdmin :Boolean
):RecyclerItem,AdapterClick

//for user page
data class UserDetail(
    @field:Json(name = "id") val id:Int,
    @field:Json(name = "avatar_url") val headPic :String,
    @field:Json(name = "login") val login:String,
    @field:Json(name="site_admin") val isAdmin :Boolean,
    @field:Json(name = "name") val name : String,
    @field:Json(name = "bio") val bio : String,
    @field:Json(name = "blog") val blog : String,
    @field:Json(name = "location") val location : String
)