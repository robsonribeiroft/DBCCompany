package com.rrdev.data_remote.util

import retrofit2.Call
import retrofit2.Response

class RequestServiceWrapperImpl: RequestServiceWrapper {

    override suspend fun <T> wrapper(request: suspend () -> Call<T>): T {

        try {
            val response: Response<T> = request().execute()
            if (response.isSuccessful)
                return response.body()!!
            throw Exception("Error: ${response.code()}\n${response.message()}")

        }catch (e: Exception){
            throw Exception("Error: ${e.message}")
        }
    }

    override suspend fun <T> wrapperBodiless(request: suspend () -> Call<T>): Boolean {
        try {
            val response: Response<T> = request().execute()
            return response.isSuccessful
        }catch (e: Exception){
            throw Exception("Error: ${e.message}")
        }
    }

}