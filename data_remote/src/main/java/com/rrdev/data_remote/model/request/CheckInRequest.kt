package com.rrdev.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class CheckInRequest(
    @SerializedName("eventId")
    val event: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)