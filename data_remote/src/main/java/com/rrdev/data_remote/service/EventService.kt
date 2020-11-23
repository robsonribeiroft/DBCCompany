package com.rrdev.data_remote.service

import com.rrdev.data_remote.model.request.CheckInRequest
import com.rrdev.data_remote.model.response.EventResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("events")
    fun getAllEvents(): Call<List<EventResponse>>

    @GET("events/{eventId}")
    fun getEventDetail(
        @Path("eventId") eventId: String
    ): Call<EventResponse>

    @POST("checkin")
    fun saveCheckIn(
        @Body checkInRequest: CheckInRequest
    ): Call<ResponseBody>
}