package com.shift.shiftfinal.domain.repository

import com.shift.shiftfinal.domain.entity.AuthEntity

interface AuthRepository {

    suspend fun login(authEntity: AuthEntity)

    suspend fun register(authEntity: AuthEntity)

    fun logout()

    val isLoggedIn: Boolean
}