package com.timhuang.higgsquizzes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.timhuang.higgsquizzes.data.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(user: User, listener: AdapterListener?){
        itemView.tv_login.text = user.login
        with(itemView.iv_headshot){
            com.bumptech.glide.Glide.with(this)
                .load(user.headPic)
                .thumbnail(0.05f)
                .error(com.timhuang.higgsquizzes.R.drawable.ic_baseline_person_24)
                .into(this)
        }
        if (user.isAdmin){
            itemView.admin_badge.visibility = View.VISIBLE
        }else{
            itemView.admin_badge.visibility = View.GONE
        }
        itemView.setOnClickListener {
            listener?.listenClick(user)
        }
    }
}

class FollowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(user: User){
        itemView.tv_login.text = user.login
        with(itemView.iv_headshot){
            com.bumptech.glide.Glide.with(this)
                .load(user.headPic)
                .thumbnail(0.05f)
                .error(com.timhuang.higgsquizzes.R.drawable.ic_baseline_person_24)
                .into(this)
        }
    }
}
