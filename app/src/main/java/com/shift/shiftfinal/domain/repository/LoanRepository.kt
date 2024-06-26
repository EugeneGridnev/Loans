package com.shift.shiftfinal.domain.repository

import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.entity.LoanEntity

interface LoanRepository {

    suspend fun getMyLoans(): List<LoanEntity>

    suspend fun getLoanCondition(): LoanConditionEntity

    suspend fun getLoanDetails(loanId: Int): LoanEntity
}