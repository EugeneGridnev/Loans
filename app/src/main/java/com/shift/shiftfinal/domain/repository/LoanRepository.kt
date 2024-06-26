package com.shift.shiftfinal.domain.repository

import com.shift.shiftfinal.data.model.LoanRequest
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.domain.entity.LoanRequestEntity

interface LoanRepository {

    suspend fun getMyLoans(): List<LoanEntity>

    suspend fun getLoanCondition(): LoanConditionEntity

    suspend fun getLoanDetails(loanId: Int): LoanEntity

    suspend fun createLoan(loanRequest: LoanRequestEntity): LoanEntity
}