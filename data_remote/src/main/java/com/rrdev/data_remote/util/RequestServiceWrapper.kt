package com.rrdev.data_remote.util

import retrofit2.Call

interface RequestServiceWrapper {

    suspend fun <T> wrapper(
            request: suspend ()-> Call<T>
    ): T

    suspend fun <T> wrapperBodiless(
            request: suspend ()-> Call<T>
    ): Boolean
}