package com.shift.shiftfinal.data.model

import com.shift.shiftfinal.domain.entity.LoanState
import java.time.OffsetDateTime

data class Loan(
    val amount: Int,
    val date: OffsetDateTime,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
)