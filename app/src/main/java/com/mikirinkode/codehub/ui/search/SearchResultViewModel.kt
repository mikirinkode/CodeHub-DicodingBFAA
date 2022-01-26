package com.mikirinkode.codehub.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikirinkode.codehub.api.RetrofitClient
import com.mikirinkode.codehub.data.model.User
import com.mikirinkode.codehub.data.model.SearchUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultViewModel : ViewModel() {

    private val listUsers = MutableLiveData<ArrayList<User>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _onFailure = MutableLiveData<Boolean>()
    val onFailure : LiveData<Boolean> = _onFailure

    private val _totalUserFound = MutableLiveData<Int>()
    val totalUserFound : LiveData<Int> = _totalUserFound

    fun setSearchUser(query: String){
        _onFailure.value = false
        _isLoading.value = true

        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<SearchUserResponse>{
                override fun onResponse(
                    call: Call<SearchUserResponse>,
                    responseSearch: Response<SearchUserResponse>
                ) {
                    _isLoading.value = false
                    _onFailure.value = false
                    if(responseSearch.isSuccessful){
                        listUsers.postValue(responseSearch.body()?.listSearchResult)
                        _totalUserFound.value = responseSearch.body()?.totalUserFound
                        Log.e("SearchResult", "onResponse: succes")
                    }
                }

                override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                    Log.e("SearchResult", "onFailure: ${t.message}")
                    _isLoading.value = false
                    _onFailure.value = true
                }

            })

    }
    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }


}