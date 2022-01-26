package com.mikirinkode.codehub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikirinkode.codehub.api.RetrofitClient
import com.mikirinkode.codehub.data.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel : ViewModel(){

    private val listUsers = MutableLiveData<ArrayList<UsersResponse>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _onFailure = MutableLiveData<Boolean>()
    val onFailure : LiveData<Boolean> = _onFailure

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        findUsers()
    }

    fun findUsers(){
        _onFailure.value = false
        _isLoading.value = true



        RetrofitClient.apiInstance.getUsers().enqueue(object : Callback<ArrayList<UsersResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UsersResponse>>,
                response: Response<ArrayList<UsersResponse>>
            ) {
                _isLoading.value = false
                _onFailure.value = false
                if(response.isSuccessful){
                    listUsers.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<ArrayList<UsersResponse>>, t: Throwable) {
                _isLoading.value = false
                _onFailure.value = true
                Log.e(TAG, "onFailure : ${t.message}")

            }

        })
    }

    fun getListUsers(): LiveData<ArrayList<UsersResponse>>{
        return listUsers
    }
}