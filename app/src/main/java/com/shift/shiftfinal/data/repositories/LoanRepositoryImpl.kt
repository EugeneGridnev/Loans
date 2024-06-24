package com.shift.shiftfinal.data.repositories

import com.shift.shiftfinal.data.AuthorizationService
import com.shift.shiftfinal.data.api.AuthApi
import com.shift.shiftfinal.data.api.LoanApi
import com.shift.shiftfinal.data.converter.Converter
import com.shift.shiftfinal.domain.entity.AuthEntity
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.repository.AuthRepository
import com.shift.shiftfinal.domain.repository.LoanRepository
import retrofit2.HttpException
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val loanApi: LoanApi,
) : LoanRepository {

    override suspend fun getLoanCondition(): LoanConditionEntity {
        val result = withToken {
            loanApi.getLoanConditions(it)
        }

        if (result.isSuccessful) {
            return Converter.loanConditionToLoanConditionEntity(result.body()!!)
        }
        when(result.code()) {
            401,403 -> throw AuthException("Token is dead")

            else -> throw ApiException(cause = HttpException(result))
        }
    }

    private inline fun <T> withToken(block: (String) -> T): T {
        val token = authorizationService.token ?: throw AuthException()
        return try {
            block(token)
        } catch (e: Exception) {
            throw ApiException(cause = e)
        }
    }
}