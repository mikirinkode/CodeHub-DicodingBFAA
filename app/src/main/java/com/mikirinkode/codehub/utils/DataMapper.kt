package com.mikirinkode.codehub.utils

import com.mikirinkode.codehub.data.model.UserEntity
import com.mikirinkode.codehub.data.source.remote.responses.UserResponse

object DataMapper {

    /*
        Change Response data to Entity
     */
    fun mapResponseToEntity(response: UserResponse): UserEntity{
        return UserEntity(
            response.id,
            response.username,
            response.avatarUrl,
            response.htmlUrl
        )
    }

    /*
        Change Response data list to Entity list
    */
    fun mapResponsesToEntities(responses: List<UserResponse>): List<UserEntity>{
        return responses.map {
            mapResponseToEntity(it)
        }
    }
}