package com.rrdev.presentation_event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rrdev.base_presentation.*
import com.rrdev.domain.model.Event
import com.rrdev.domain.usecase.GetAllEvents

class EventListViewModel(
    app: Application,
    private val getAllEvents: GetAllEvents
): AndroidViewModel(app) {

    private val _allEvents by viewState<List<Event>>()
    val allEvents = _allEvents.asLiveData()

    fun fetchEvents(){
        _allEvents.postLoading()
        getAllEvents(
            onSuccess = {
                _allEvents.postSuccess(it)
            },
            onError = {
                _allEvents.postError(it)
            },
            scope = viewModelScope
        )
    }
}