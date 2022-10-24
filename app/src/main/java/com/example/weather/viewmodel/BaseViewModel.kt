package com.example.weather.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.util.Event

abstract class BaseViewModel : ViewModel() {

    private val _liveDataExceptionMessage = MutableLiveData<Event<Int>>()
    val liveDataExceptionMessage: LiveData<Event<Int>> = _liveDataExceptionMessage

    fun setMessage(@StringRes message: Int) {
        _liveDataExceptionMessage.value = Event(message)
    }

}