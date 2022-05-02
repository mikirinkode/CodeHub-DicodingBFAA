package com.mikirinkode.codehub.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mikirinkode.codehub.data.model.FavoriteUser
import com.mikirinkode.codehub.data.source.local.FavoriteUserDao
import com.mikirinkode.codehub.data.source.local.UserDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init{
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser():LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }
}