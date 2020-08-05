package com.timhuang.higgsquizzes.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.timhuang.higgsquizzes.data.User
import com.timhuang.higgsquizzes.data.UserDetail
import com.timhuang.higgsquizzes.helper.EventWrapper
import com.timhuang.higgsquizzes.helper.Result
import com.timhuang.higgsquizzes.model.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2020/8/4.
 **/

class UserUseCase (private val scope:CoroutineScope,private val repository: UserRepository) {

    val user = MutableLiveData<UserDetail>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<EventWrapper<String>>()
    val biFollows = MutableLiveData<List<User>>()

    private val perPage = 100
    private var pageFollower = 1
    private var pageFollowing = 1
    private var moreFollower = true
    private var moreFollowing = true
    private val followingSet = linkedSetOf<User>()
    private val followerSet = linkedSetOf<User>()

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

    fun biFollows(userName: String): LiveData<List<User>> {
        scope.launch {
            if (!isActive) return@launch
            updateFollowing(userName)
            updateFollower(userName)

            if (!moreFollowing && !moreFollower){
                return@launch
            }
            updateBiFollows()
            biFollows(userName)
        }
        return biFollows
    }

    private suspend fun updateFollowing(userName: String) {
        if (moreFollowing){
            val following = repository.getFollowings(userName,pageFollowing,perPage)
            if (following is Result.Success){
                followingSet.addAll(following.data)
                if (following.data.size<perPage){
                    moreFollowing = false
                }else{
                    pageFollowing++
                }
            }else{
                moreFollowing = false
            }
        }
    }

    private suspend fun updateFollower(userName: String) {
        if (moreFollower){
            val follower = repository.getFollowers(userName,pageFollower,perPage)
            if (follower is Result.Success){
                followerSet.addAll(follower.data)
                if (follower.data.size<perPage){
                    moreFollower = false
                }else{
                    pageFollower++
                }
            }else{
                moreFollower = false
            }
        }
    }

    private fun updateBiFollows() {
        val subSet  =  followingSet.intersect(followerSet)
        biFollows.value = biFollows.value?.let { list-> list + subSet.toList() } ?: subSet.toList()
        followingSet.removeAll(subSet)
        followerSet.removeAll(subSet)
    }

    fun cancel(){
        scope.cancel()
    }
}