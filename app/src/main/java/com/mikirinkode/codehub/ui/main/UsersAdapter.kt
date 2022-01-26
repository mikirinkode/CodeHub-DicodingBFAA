package com.mikirinkode.codehub.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikirinkode.codehub.data.model.UsersResponse
import com.mikirinkode.codehub.databinding.ItemUserBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){

    private val list = ArrayList<UsersResponse>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setList(userResponse: ArrayList<UsersResponse>){
        list.clear()
        list.addAll(userResponse)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(userResponse: UsersResponse){
            binding.apply {
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(userResponse)
                }
                tvItemUsername.text = userResponse.username
                tvItemUserUrl.text = userResponse.htmlUrl
                Glide.with(itemView)
                    .load(userResponse.avatarUrl)
                    .centerCrop()
                    .into(ivItemProfile)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        fun onItemClicked(data: UsersResponse)
    }
}