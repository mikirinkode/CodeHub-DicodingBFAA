package com.mikirinkode.codehub.data.model

import com.google.gson.annotations.SerializedName

data class UsersResponse (
    @SerializedName("login")
    val username : String,
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("html_url")
    val htmlUrl : String,
    val id: Int

    )