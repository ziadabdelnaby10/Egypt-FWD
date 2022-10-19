package com.example.asteroidsproject.model

import com.example.asteroidsproject.database.PictureOfTheDayEntity
import com.example.asteroidsproject.utils.Constants
import com.squareup.moshi.Json

data class PictureOfDay(
    @Json(name = "media_type") var mediaType: String,
    var title: String,
    var url: String
)

fun PictureOfTheDayEntity.asDomainModel() : PictureOfDay = PictureOfDay(
    title = this.title,
    url = this.url,
    mediaType =  Constants.IMAGE_MEDIA_TYPE
)