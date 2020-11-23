package com.rrdev.data_remote.mapper

import com.rrdev.data_remote.model.response.EventResponse
import com.rrdev.data_remote.model.response.PeopleResponse
import com.rrdev.domain.model.Event
import com.rrdev.domain.model.People
import java.util.*

object EventMapper {

    fun eventResponseToEventDomain(response: EventResponse): Event{
        return Event(
            id = response.id,
            title = response.title,
            description = response.description,
            date = convertToDate(response.date),
            latitude = response.latitude,
            longitude = response.longitude,
            image = response.image,
            people = response.people.map(this::peopleResponseToPeopleDomain)
        )
    }

    private fun convertToDate(timeStamp: Long) = Calendar.getInstance(Locale.getDefault()).apply {
        timeInMillis = timeStamp * 1000
    }.time

    private fun peopleResponseToPeopleDomain(response: PeopleResponse): People =
        People(name = response.name, email = response.email)
}