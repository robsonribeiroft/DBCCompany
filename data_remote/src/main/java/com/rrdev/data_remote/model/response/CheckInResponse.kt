package com.rrdev.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class CheckInResponse(
    @SerializedName("eventId")
    val event: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)