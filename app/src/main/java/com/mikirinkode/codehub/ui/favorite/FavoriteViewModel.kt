package com.mikirinkode.codehub.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mikirinkode.codehub.data.model.UserEntity
import com.mikirinkode.codehub.data.source.local.CodehubDao
import com.mikirinkode.codehub.data.source.local.CodehubDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var userDao: CodehubDao?
    private var codehubDb: CodehubDatabase? = CodehubDatabase.getDatabase(application)

    init{
        userDao = codehubDb?.favoriteUserDao()
    }

    fun getFavoriteUser():LiveData<List<UserEntity>>?{
        return userDao?.getFavoriteUser()
    }

    
}