package com.example.asteroidsproject.ui.mainpage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asteroidsproject.api.getSeventhDay
import com.example.asteroidsproject.api.getToday
import com.example.asteroidsproject.database.AsteroidDatabase
import com.example.asteroidsproject.database.asDomainModel
import com.example.asteroidsproject.model.Asteroid
import com.example.asteroidsproject.model.PictureOfDay
import com.example.asteroidsproject.model.asDomainModel
import com.example.asteroidsproject.repository.AsteroidRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AsteroidDatabase.getDatabase(application)
    private val asteroidRepo = AsteroidRepo(database)

    private val _loadedAsteroids = MutableLiveData<List<Asteroid>>()
    val loadAsteroids: LiveData<List<Asteroid>>
        get() = _loadedAsteroids

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _downloadingData = MutableLiveData(true)
    val downloadingData: LiveData<Boolean>
        get() = _downloadingData

    private val _downloadingImage = MutableLiveData(true)
    val downloadingImage: LiveData<Boolean>
        get() = _downloadingImage


    init {
        viewModelScope.launch {
            try {
                refreshPicture()
            } catch (exception: Exception) {
                Timber.e("error in getting picture of the day data ${exception.message}")
                _downloadingImage.value = false
            }

            try {
                refreshData()
            } catch (exception: Exception) {
                Timber.e("error in refreshing data ${exception.message}")
                _downloadingData.value = false
            }

            try {
                getDataFromDatabaseDaily()
            } catch (exception: Exception) {
                Timber.e("error in getting data from database ${exception.message}")
            }

            try {
                getPictureFromDatabase()
            } catch (ex: Exception) {
                Timber.e("error in getting picture of the day from database ${ex.message}")
            }

        }

    }

     fun refreshData() {
        viewModelScope.launch {
            try {
                asteroidRepo.refreshData()
                _downloadingData.value = true
            } catch (exception: Exception) {
                Timber.e("error in refreshing data ${exception.message}")
                _downloadingData.value = false
            }
        }
    }

     fun refreshPicture() {
        viewModelScope.launch {
            try {
                asteroidRepo.refreshPictureOfDay()
                _downloadingImage.value = true
            } catch (exception: Exception) {
                Timber.e("error in getting picture of the day data ${exception.message}")
                _downloadingImage.value = false
            }
        }
    }



    fun getDataFromDatabaseWeekly() {
        viewModelScope.launch {
            _loadedAsteroids.value =
                database.asteroidDAO.getAsteroidsByCloseApproachDate(getToday(), getSeventhDay())
                    .asDomainModel()
        }
    }

    fun getDataFromDatabaseDaily() {
        viewModelScope.launch {
            _loadedAsteroids.value =
                database.asteroidDAO.getAsteroidsByCloseApproachDate(getToday(), getToday())
                    .asDomainModel()

        }
    }

    fun getDataFromDatabaseAll() {
        viewModelScope.launch {
            _loadedAsteroids.value = database.asteroidDAO.getAllAsteroids().asDomainModel()
        }
    }

    private fun getPictureFromDatabase() {
        viewModelScope.launch {
            _pictureOfDay.value = database.pictureDAO.getPicture().asDomainModel()
        }
    }
}
