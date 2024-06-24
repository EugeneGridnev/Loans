package com.shift.shiftfinal.domain.repository

import com.shift.shiftfinal.domain.entity.LoanConditionEntity

interface LoanRepository {

    suspend fun getLoanCondition(): LoanConditionEntity
}