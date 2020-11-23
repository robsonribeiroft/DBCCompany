package com.rrdev.domain.usecase

import com.rrdev.domain.model.Event
import com.rrdev.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetAllEvents(
    private val eventRepository: EventRepository
): UseCase<List<Event>, Unit>() {

    override fun run(params: Unit?): Flow<List<Event>> = eventRepository.getAllEvents()
}