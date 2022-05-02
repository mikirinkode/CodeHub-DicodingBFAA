package com.mikirinkode.codehub.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("login")
    val username : String,
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("html_url")
    val htmlUrl : String,
    @SerializedName("id")
    val id: Int

    )