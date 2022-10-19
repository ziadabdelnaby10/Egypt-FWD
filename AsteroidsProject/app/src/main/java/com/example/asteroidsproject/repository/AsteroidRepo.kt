package com.example.asteroidsproject.repository

import com.example.asteroidsproject.api.*
import com.example.asteroidsproject.database.AsteroidDatabase
import com.example.asteroidsproject.database.asDatabaseModel
import com.example.asteroidsproject.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject

class AsteroidRepo(private val database: AsteroidDatabase) {

    suspend fun refreshData() {
        val startDate = getToday()
        val endDate = getSeventhDay()

        withContext(Dispatchers.IO){
            val asteroidResponseBody: ResponseBody =
                Network.asteroids.getAsteroidsAsync(startDate, endDate, Constants.API_KEY).await()
            val asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody.string()))
            database.asteroidDAO.insertAll(*asteroidList.asDatabaseModel())
        }
    }

    suspend fun refreshPictureOfDay(){
        withContext(Dispatchers.IO){
            val pictureOfDay = Network.asteroids.getPictureOfDayAsync(Constants.API_KEY).await()
            if (pictureOfDay.mediaType == Constants.IMAGE_MEDIA_TYPE) {
                database.pictureDAO.insert(pictureOfDay.asDatabaseModel())
            }
        }
    }

    suspend fun deletePreviousDayAsteroids() {
            withContext(Dispatchers.IO){
                database.asteroidDAO.deletePreviousDayAsteroids(getToday())
            }
    }
}