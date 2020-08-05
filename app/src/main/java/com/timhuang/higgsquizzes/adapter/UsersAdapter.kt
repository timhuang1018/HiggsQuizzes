package com.timhuang.higgsquizzes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.timhuang.higgsquizzes.R
import com.timhuang.higgsquizzes.data.User

/**
 * to simplify the code, UsersAdapter just has one viewtype
 */
class UsersAdapter(private val listener: AdapterListener?,private val type:ListType): ListAdapter<RecyclerItem,RecyclerView.ViewHolder>(AdapterDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(type){
            ListType.USERS->{
                UserViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
                )
            }
            ListType.FOLLOWS->{
                FollowViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.card_user,parent,false)
                )
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item is User){
            if (holder is UserViewHolder){
                holder.bind(user = item,listener = listener)
            }else if (holder is FollowViewHolder){
                holder.bind(user = item)
            }
        }
    }

}

//not considering about item changed
object AdapterDiff: DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem) = oldItem.id == newItem.id
}

interface RecyclerItem{
    val id :Int
}

interface AdapterClick
interface AdapterListener{
    fun listenClick(item:AdapterClick)
}

enum class ListType{
    USERS,FOLLOWS
}