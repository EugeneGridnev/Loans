package com.shift.shiftfinal.data.repositories

import com.shift.shiftfinal.data.AuthorizationService
import com.shift.shiftfinal.data.api.LoanApi
import com.shift.shiftfinal.data.converter.Converter
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.repository.LoanRepository
import retrofit2.HttpException
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val loanApi: LoanApi,
) : LoanRepository {
    override suspend fun getMyLoans(): List<LoanEntity> {
        val result = withToken {
            loanApi.getLoans(it)
        }

        if (result.isSuccessful) {
            return result.body()!!.map {
                Converter.loanToLoanEntity(it)
            }
        }
        when (result.code()) {
            401, 403 -> throw AuthException("Token is dead")

            else -> throw ApiException(cause = HttpException(result))
        }
    }

    override suspend fun getLoanCondition(): LoanConditionEntity {
        val result = withToken {
            loanApi.getLoanConditions(it)
        }

        if (result.isSuccessful) {
            return Converter.loanConditionToLoanConditionEntity(result.body()!!)
        }
        when (result.code()) {
            401, 403 -> throw AuthException("Token is dead")

            else -> throw ApiException(cause = HttpException(result))
        }
    }

    override suspend fun getLoanDetails(loanId: Int): LoanEntity {
        val result = withToken {
            loanApi.getLoanData(it, loanId)
        }

        if (result.isSuccessful) {
            return Converter.loanToLoanEntity(result.body()!!)
        }
        when (result.code()) {
            401, 403 -> throw AuthException("Token is dead")

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