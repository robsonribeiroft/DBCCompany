package com.rrdev.domain.model

import java.util.*

data class Event(
    val id: String,
    val title: String,
    val latitude: String,
    val longitude: String,
    val image: String,
    val description: String,
    val date: Date,
    val people: List<People>
)