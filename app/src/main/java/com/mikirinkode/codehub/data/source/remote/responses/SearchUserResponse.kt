package com.mikirinkode.codehub.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class SearchUserResponse (

    @SerializedName("total_count")
    val totalUserFound: Int,
    @SerializedName("items")
    val listSearchResult: ArrayList<UserResponse>
)