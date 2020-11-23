package com.rrdev.domain.usecase

import com.rrdev.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class RegisterInEvent(
    private val eventRepository: EventRepository
): UseCase<Boolean, RegisterInEvent.Params>()  {

    override fun run(params: Params?): Flow<Boolean> = when{
        params == null -> throw Exception("RegisterInEvent missing Params")
        params.eventId.isBlank() -> throw Exception("RegisterInEvent invalid params")
        else -> eventRepository.registerInEvent(params.eventId, params.name, params.email)
    }

    data class Params(
        val eventId: String,
        val name: String,
        val email: String
    )
}