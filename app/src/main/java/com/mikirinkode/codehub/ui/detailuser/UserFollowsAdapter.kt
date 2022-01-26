package com.mikirinkode.codehub.ui.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikirinkode.codehub.data.model.User
import com.mikirinkode.codehub.databinding.ItemUserDetailBinding

class UserFollowsAdapter : RecyclerView.Adapter<UserFollowsAdapter.UserViewHolder>() {
    private val list = ArrayList<User>()


    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(ivItemProfile)
                tvItemUsername.text = user.login
                tvItemUserUrl.text = user.htmlUrl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}