package com.example.asteroidsproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDAO {
    @Query("SELECT * FROM asteroid WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate ASC")
    suspend fun getAsteroidsByCloseApproachDate(
        startDate: String,
        endDate: String
    ): List<AsteroidEntity>

    @Query("SELECT * FROM asteroid ORDER BY closeApproachDate ASC")
    suspend fun getAllAsteroids(): List<AsteroidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: AsteroidEntity)

    @Query("DELETE FROM asteroid WHERE closeApproachDate < :today")
    suspend fun deletePreviousDayAsteroids(today: String)
}

@Dao
interface PictureDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictureOfTheDayEntity: PictureOfTheDayEntity)

    @Query("SELECT * FROM picture WHERE id=1")
    suspend fun getPicture() : PictureOfTheDayEntity
}