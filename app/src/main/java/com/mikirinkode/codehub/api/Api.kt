package com.mikirinkode.codehub.api

import com.mikirinkode.codehub.data.model.DetailUserResponse
import com.mikirinkode.codehub.data.model.SearchUserResponse
import com.mikirinkode.codehub.data.model.User
import com.mikirinkode.codehub.data.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object{
        const val GITHUB_TOKEN = "your_github_token"
    }

    @GET("users")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getUsers(): Call<ArrayList<UsersResponse>>

    @GET("search/users")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}