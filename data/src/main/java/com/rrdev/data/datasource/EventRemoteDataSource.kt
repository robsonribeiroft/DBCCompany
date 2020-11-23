package com.rrdev.data.datasource

import com.rrdev.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    fun getAllEvents(): Flow<List<Event>>
    fun getEventDetail(eventId: String): Flow<Event>
    fun registerInEvent(eventId: String, name: String, email: String): Flow<Boolean>
}