package com.example.shoestoreapp

import androidx.databinding.InverseMethod

object Converter {

    @InverseMethod("doubleToString")
    @JvmStatic fun stringToDouble(value : String?) : Double{
        return if(value.isNullOrEmpty()) 0.0 else value.toDouble()
    }
    @JvmStatic fun doubleToString(value : Double?) : String{
        return value.toString() ?: ""
    }

}