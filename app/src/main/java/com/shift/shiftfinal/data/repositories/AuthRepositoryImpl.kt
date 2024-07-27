package com.shift.shiftfinal.data.repositories

import com.shift.shiftfinal.data.AuthorizationService
import com.shift.shiftfinal.data.api.AuthApi
import com.shift.shiftfinal.data.converter.Converter
import com.shift.shiftfinal.domain.entity.AuthEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.exceptions.UserNameTakenException
import com.shift.shiftfinal.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authApi: AuthApi,
) : AuthRepository {

    override val isLoggedIn: Boolean
        get() = authorizationService.token != null

    override suspend fun login(authEntity: AuthEntity) {
        val result = try {
            authApi.login(Converter.authEntityToAuth(authEntity))
        } catch (e: Exception) {
            throw ApiException(cause = e)
        }

        if (result.isSuccessful) {
            authorizationService.setToken(result.body())
            return
        }
        when (result.code()) {
            404 -> throw AuthException("404 password problems")
            else -> throw ApiException(cause = HttpException(result))
        }

    }

    override suspend fun register(authEntity: AuthEntity) {
        val result = try {
            authApi.register(Converter.authEntityToAuth(authEntity))
        } catch (e: Exception) {
            throw ApiException(cause = e)
        }

        if (result.isSuccessful) {
            return
        }
        when (result.code()) {
            400 -> throw UserNameTakenException("User name already taken")
            else -> throw ApiException(cause = HttpException(result))
        }
    }

    override fun logout() {
        authorizationService.setToken(null)
    }


}