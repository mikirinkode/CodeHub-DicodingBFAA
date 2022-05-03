package com.mikirinkode.codehub.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mikirinkode.codehub.data.model.UserEntity
import com.mikirinkode.codehub.data.source.local.CodeHubDao
import com.mikirinkode.codehub.data.source.local.CodeHubDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var dao: CodeHubDao?
    private var database: CodeHubDatabase? = CodeHubDatabase.getDatabase(application)

    init{
        dao = database?.favoriteUserDao()
    }

    fun getFavoriteUser():LiveData<List<UserEntity>>?{
        return dao?.getFavoriteUser()
    }

    
}