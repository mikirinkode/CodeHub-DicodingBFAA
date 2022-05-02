package com.mikirinkode.codehub.utils

import com.mikirinkode.codehub.BuildConfig

class Constants {
    companion object {
        const val GITHUB_TOKEN = BuildConfig.GITHUB_API_KEY
        const val BASE_URL = "https://api.github.com/"
    }
}