package com.mikirinkode.codehub.data.source.remote

import com.mikirinkode.codehub.BuildConfig
import com.mikirinkode.codehub.data.source.remote.responses.DetailUserResponse
import com.mikirinkode.codehub.data.source.remote.responses.SearchUserResponse
import com.mikirinkode.codehub.data.source.remote.responses.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object{
        const val GITHUB_TOKEN = BuildConfig.GITHUB_API_KEY
    }

    @GET("users")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getUsers(): Call<ArrayList<UserResponse>>

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
    ): Call<ArrayList<UserResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $GITHUB_TOKEN")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserResponse>>

}