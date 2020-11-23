package com.rrdev.data.repository

import com.rrdev.data.datasource.EventRemoteDataSource
import com.rrdev.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class EventRepositoryImpl(
    private val eventRemoteDataSource: EventRemoteDataSource
): EventRepository {

    override fun getAllEvents() = eventRemoteDataSource.getAllEvents()

    override fun getEventDetail(eventId: String) = eventRemoteDataSource.getEventDetail(eventId)

    override fun registerInEvent(eventId: String, name: String, email: String) =
        eventRemoteDataSource.registerInEvent(eventId, name, email)
}