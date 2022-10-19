package com.example.asteroidsproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidsproject.model.PictureOfDay
import com.example.asteroidsproject.utils.Constants

@Entity(tableName = Constants.PICTURE_TABLE_NAME)
data class PictureOfTheDayEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Long = 1,
    var url: String,
    var title: String
)

fun PictureOfDay.asDatabaseModel() : PictureOfTheDayEntity = PictureOfTheDayEntity(
    url = this.url,
    title = this.title
)