package com.shift.shiftfinal.domain.entity

import java.time.OffsetDateTime

data class LoanEntity(
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