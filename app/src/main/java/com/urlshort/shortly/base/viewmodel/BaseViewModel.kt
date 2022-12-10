package com.urlshort.shortly.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urlshort.shortly.base.model.data.UiDataState
import kotlinx.coroutines.launch

abstract class BaseViewModel<Model, Event> : ViewModel() {

    private val dataStateMutableLiveData: MutableLiveData<UiDataState<Model>> = MutableLiveData()
    val dataState: LiveData<UiDataState<Model>> = dataStateMutableLiveData

    fun setStateForEvent(event: Event) {
        viewModelScope.launch {
            doActionForEvent(event)
        }
    }

    protected abstract suspend fun doActionForEvent(event: Event)

}