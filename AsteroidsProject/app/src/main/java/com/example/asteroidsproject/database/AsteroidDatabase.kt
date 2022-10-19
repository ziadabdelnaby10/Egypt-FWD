package com.example.asteroidsproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asteroidsproject.utils.Constants

@Database(entities = [AsteroidEntity::class , PictureOfTheDayEntity::class] , version = 1)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDAO : AsteroidDAO
    abstract val pictureDAO : PictureDAO

    companion object{
        @Volatile
        private lateinit var INSTANCE : AsteroidDatabase

        fun getDatabase(context: Context): AsteroidDatabase{
            synchronized(AsteroidDatabase::class.java){
                if(!::INSTANCE.isInitialized){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        Constants.ASTEROID_DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}