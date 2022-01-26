package com.mikirinkode.codehub.data.model

import com.google.gson.annotations.SerializedName

data class SearchUserResponse (

    @SerializedName("total_count")
    val totalUserFound: Int,
    @SerializedName("items")
    val listSearchResult: ArrayList<User>
)