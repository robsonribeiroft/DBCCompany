package com.rrdev.data_remote.datasource

import com.rrdev.data.datasource.EventRemoteDataSource
import com.rrdev.data_remote.mapper.EventMapper
import com.rrdev.data_remote.model.request.CheckInRequest
import com.rrdev.data_remote.service.EventService
import com.rrdev.data_remote.util.RequestServiceWrapper
import com.rrdev.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRemoteDataSourceImpl(
    private val eventService: EventService,
    private val requestServiceWrapper: RequestServiceWrapper
): EventRemoteDataSource {

    override fun getAllEvents(): Flow<List<Event>> = flow {
        emit(
            requestServiceWrapper.wrapper { eventService.getAllEvents() }.map {
                EventMapper.eventResponseToEventDomain(it)
            }
        )
    }

    override fun getEventDetail(eventId: String): Flow<Event> = flow {
        emit(
            EventMapper.eventResponseToEventDomain(
                requestServiceWrapper.wrapper { eventService.getEventDetail(eventId) }
            )
        )
    }

    override fun registerInEvent(eventId: String, name: String, email: String) = flow {
        emit(
            requestServiceWrapper.wrapperBodiless {
                eventService.saveCheckIn(CheckInRequest(eventId, name, email))
            }
        )
    }
}