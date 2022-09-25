package com.example.shoestoreapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestoreapp.model.Shoe
import timber.log.Timber

class SharedViewModel : ViewModel() {

    var shoe = Shoe()

    private val shoesList = mutableListOf<Shoe>()
    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun onSave() {
        shoesList.add(shoe)
        Timber.tag("ViewModel").e(shoe.toString())
        shoe = Shoe("", 0.0, "", "")
        _shoeList.value = shoesList
    }
}