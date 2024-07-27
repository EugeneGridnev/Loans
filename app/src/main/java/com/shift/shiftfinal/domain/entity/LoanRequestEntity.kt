package com.shift.shiftfinal.domain.entity

data class LoanRequestEntity(
    val amount: Int,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
)