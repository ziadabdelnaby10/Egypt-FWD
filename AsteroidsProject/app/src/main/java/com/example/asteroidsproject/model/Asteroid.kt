package com.example.asteroidsproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid(
    var id: Long,
    var codename: String,
    var closeApproachDate: String,
    var absoluteMagnitude: Double,
    var estimatedDiameter: Double,
    var relativeVelocity: Double,
    var distanceFromEarth: Double,
    var isPotentiallyHazardous: Boolean
) : Parcelable