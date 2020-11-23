package com.rrdev.presentation_event

import android.app.Application
import androidx.lifecycle.*
import com.rrdev.base_presentation.*
import com.rrdev.domain.model.Event
import com.rrdev.domain.usecase.GetEventDetail
import com.rrdev.domain.usecase.RegisterInEvent

class EventDetailViewModel(
    app: Application,
    private val getEventDetail: GetEventDetail,
    private val registerInEvent: RegisterInEvent
): AndroidViewModel(app) {

    private val _detailEvent by viewState<Event>()
    private val _registerEvent by viewState<Boolean>()

    val detailEvent = _detailEvent.asLiveData()
    val registerEvent = _registerEvent.asLiveData()

    var currentEvent: Event? = null

    fun fetchEventDetail(eventId: String){
        _detailEvent.postLoading()
        getEventDetail(
            params = GetEventDetail.Params(
                eventId = eventId
            ),
            onSuccess = {
                currentEvent = it
                _detailEvent.postSuccess(it)
            },
            onError = {
                _detailEvent.postError(it)
            },
            scope = viewModelScope
        )
    }

    fun registerUserInEvent(eventId: String, name: String, email: String){
        _registerEvent.postLoading()
        registerInEvent(
            params = RegisterInEvent.Params(
                eventId = eventId,
                name = name,
                email = email
            ),
            onSuccess = {
                _registerEvent.postSuccess(it)
            },
            onError = {
                _registerEvent.postError(it)
            }
        )
    }
}