package com.urlshort.shortly.base.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urlshort.shortly.base.model.data.UiDataState
import kotlinx.coroutines.launch

abstract class BaseViewModel<Model, Event> : ViewModel() {

    var dataState: UiDataState<Model> by mutableStateOf(UiDataState.Loading)

    fun setStateForEvent(event: Event) {
        viewModelScope.launch {
            doActionForEvent(event)
        }
    }

    protected abstract suspend fun doActionForEvent(event: Event)

}