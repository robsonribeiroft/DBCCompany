package com.rrdev.domain.usecase

import com.rrdev.domain.model.Event
import com.rrdev.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class GetEventDetail(
    private val eventRepository: EventRepository
): UseCase<Event, GetEventDetail.Params>()  {

    override fun run(params: Params?): Flow<Event> = when{
        params == null -> throw Exception("GetEventDetail missing Params")
        params.eventId.isBlank() -> throw Exception("GetEventDetail invalid params")
        else -> eventRepository.getEventDetail(params.eventId)
    }

    data class Params(
        val eventId: String
    )
}