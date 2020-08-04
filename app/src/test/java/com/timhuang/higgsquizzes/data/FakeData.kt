package com.timhuang.higgsquizzes.data

/**
 * Created by timhuang on 2020/8/4.
 **/

object FakeData {
    val users = listOf<User>(
        User(id = 1,headPic = "",login = "Tim",isAdmin = false),
        User(id = 2,headPic = "",login = "Tim2",isAdmin = false),
        User(id = 3,headPic = "",login = "Tim3",isAdmin = false),
        User(id = 4,headPic = "",login = "Tim4",isAdmin = true)
    )
    val user = UserDetail(
        id = 1,headPic = "",login = "Tim",isAdmin = false,name = "Tim",bio = "...",blog = "",location = ""
    )
}