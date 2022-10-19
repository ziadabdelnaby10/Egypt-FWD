package com.example.asteroidsproject.utils

import com.example.asteroidsproject.BuildConfig

object Constants {
    const val ASTEROID_DATABASE_NAME = "asteroid"
    const val ASTEROID_TABLE_NAME = "asteroid"
    const val PICTURE_TABLE_NAME = "picture"
    const val IMAGE_MEDIA_TYPE = "image"
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = BuildConfig.API_KEY
    const val REFRESH_ASTEROIDS_WORK_NAME = "refreshDataWork"
    const val DELETE_ASTEROIDS_WORK_NAME = "deleteDataWork"
}