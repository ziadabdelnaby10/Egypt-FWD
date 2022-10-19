package com.example.asteroidsproject.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidsproject.database.AsteroidDatabase
import com.example.asteroidsproject.repository.AsteroidRepo
import retrofit2.HttpException

class DeleteDataWork(appContext: Context, params: WorkerParameters) :

    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabase(applicationContext)
        val repository = AsteroidRepo(database)

        return try {
            repository.deletePreviousDayAsteroids()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
}