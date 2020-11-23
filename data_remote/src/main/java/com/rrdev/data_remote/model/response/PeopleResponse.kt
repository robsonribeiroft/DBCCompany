package com.rrdev.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)
