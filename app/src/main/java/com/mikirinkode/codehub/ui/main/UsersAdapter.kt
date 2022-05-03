package com.mikirinkode.codehub.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikirinkode.codehub.data.model.UserEntity
import com.mikirinkode.codehub.databinding.ItemUserBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val list = ArrayList<UserEntity>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setList(userEntity: List<UserEntity>) {
        list.clear()
        list.addAll(userEntity)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userEntity: UserEntity) {
            binding.apply {
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(userEntity)
                }
                tvItemUsername.text = userEntity.username
                tvItemUserUrl.text = userEntity.htmlUrl
                Glide.with(itemView)
                    .load(userEntity.avatarUrl)
                    .centerCrop()
                    .into(ivItemProfile)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {

        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserEntity)
    }
}