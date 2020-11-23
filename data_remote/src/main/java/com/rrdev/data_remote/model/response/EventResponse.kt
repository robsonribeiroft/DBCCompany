package com.rrdev.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: Long,
    @SerializedName("people")
    val people: List<PeopleResponse>
)