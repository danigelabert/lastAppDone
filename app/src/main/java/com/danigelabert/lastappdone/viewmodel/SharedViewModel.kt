package com.danigelabert.lastappdone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danigelabert.lastappdone.model.Moble

class SharedViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<Moble>()
    val selectedItem: LiveData<Moble> get() = _selectedItem

    //Seleccionar item DB
    fun setSelectedItem(item: Moble) {
        _selectedItem.value = item
    }
}