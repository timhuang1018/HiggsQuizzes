package com.timhuang.higgsquizzes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.timhuang.higgsquizzes.R
import com.timhuang.higgsquizzes.data.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * to simplify the code, UsersAdapter just has one viewtype
 */
class UsersAdapter(val listener: AdapterListener): ListAdapter<RecyclerItem,RecyclerView.ViewHolder>(AdapterDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is UserViewHolder && item is User){
            holder.bind(user = item,listener = listener)
        }
    }

}

class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    fun bind(user: User,listener: AdapterListener){
        itemView.tv_login.text = user.login
        with(itemView.iv_headshot){
            Glide.with(this)
                .load(user.headPic)
                .thumbnail(0.05f)
                .error(R.drawable.ic_baseline_person_24)
                .into(this)
        }
        if (user.isAdmin){
            itemView.admin_badge.visibility = View.VISIBLE
        }else{
            itemView.admin_badge.visibility = View.GONE
        }
        itemView.setOnClickListener {
            listener.listenClick(user)
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