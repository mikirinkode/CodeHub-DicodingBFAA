package com.mikirinkode.codehub.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mikirinkode.codehub.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class CodeHubDatabase: RoomDatabase(){
    companion object {
        private var INSTANCE : CodeHubDatabase? = null

        fun getDatabase(context: Context): CodeHubDatabase?{
            if(INSTANCE == null){
                synchronized(CodeHubDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, CodeHubDatabase::class.java, "mk_codehub_db").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoriteUserDao(): CodeHubDao
}