package com.shift.shiftfinal.domain.entity

data class LoanConditionEntity(
    val maxAmount: Int,
    val percent: Double,
    val period: Int
)