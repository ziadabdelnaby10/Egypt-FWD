package com.example.shoestoreapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    var name: String = "",
    var size: Double = 0.0,
    var company: String = "",
    var description: String = "",
    val images: List<String> = mutableListOf()
)  : Parcelable