package com.shift.shiftfinal.data.api

import com.shift.shiftfinal.data.model.Auth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun login(
        @Body
        auth:Auth
    ): Response<String>

    @POST("/registration")
    suspend fun register(
        @Body
        auth:Auth
    ): Response<Unit>
}