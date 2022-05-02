package com.mikirinkode.codehub.ui.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikirinkode.codehub.data.source.remote.responses.UserResponse
import com.mikirinkode.codehub.databinding.ItemFollowerBinding
import com.mikirinkode.codehub.ui.main.UsersAdapter

class UserFollowsAdapter : RecyclerView.Adapter<UserFollowsAdapter.UserViewHolder>() {
    private val list = ArrayList<UserResponse>()
    private var onItemClickCallback: UsersAdapter.OnItemClickCallback? = null

    fun setList(users: ArrayList<UserResponse>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserResponse) {

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(ivItemProfile)
                tvItemUsername.text = user.username
                tvItemUserUrl.text = user.htmlUrl

                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setOnItemClickCallback(onItemClickCallback: UsersAdapter.OnItemClickCallback) {

        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponse)
    }
}