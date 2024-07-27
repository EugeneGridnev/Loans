package com.shift.shiftfinal.data.converter

import com.shift.shiftfinal.data.model.Auth
import com.shift.shiftfinal.data.model.Loan
import com.shift.shiftfinal.data.model.LoanCondition
import com.shift.shiftfinal.data.model.LoanRequest
import com.shift.shiftfinal.domain.entity.AuthEntity
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.domain.entity.LoanRequestEntity

object Converter {

    fun authToAuthEntity(auth: Auth): AuthEntity {

        return AuthEntity(
            name = auth.name,
            password = auth.password
        )
    }

    fun authEntityToAuth(authEntity: AuthEntity): Auth {

        return Auth(
            name = authEntity.name,
            password = authEntity.password
        )
    }

    fun loanToLoanEntity(loan: Loan): LoanEntity {

        return LoanEntity(
            amount = loan.amount,
            date = loan.date,
            firstName = loan.firstName,
            id = loan.id,
            lastName = loan.lastName,
            percent = loan.percent,
            period = loan.period,
            phoneNumber = loan.phoneNumber,
            state = loan.state
        )
    }

    fun loanEntityToLoan(loanEntity: LoanEntity): Loan {

        return Loan(
            amount = loanEntity.amount,
            date = loanEntity.date,
            firstName = loanEntity.firstName,
            id = loanEntity.id,
            lastName = loanEntity.lastName,
            percent = loanEntity.percent,
            period = loanEntity.period,
            phoneNumber = loanEntity.phoneNumber,
            state = loanEntity.state
        )
    }

    fun loanConditionToLoanConditionEntity(loanCondition: LoanCondition): LoanConditionEntity {

        return LoanConditionEntity(
            maxAmount = loanCondition.maxAmount,
            percent = loanCondition.percent,
            period = loanCondition.period
        )
    }

    fun loanConditionEntityToLoanCondition(loanConditionEntity: LoanConditionEntity): LoanCondition {

        return LoanCondition(
            maxAmount = loanConditionEntity.maxAmount,
            percent = loanConditionEntity.percent,
            period = loanConditionEntity.period
        )
    }

    fun loanRequestToLoanRequestEntity(loanRequest: LoanRequest): LoanRequestEntity {

        return LoanRequestEntity(
            amount = loanRequest.amount,
            firstName = loanRequest.firstName,
            lastName = loanRequest.lastName,
            percent = loanRequest.percent,
            period = loanRequest.period,
            phoneNumber = loanRequest.phoneNumber
        )
    }

    fun loanRequestEntityToLoanRequest(loanRequestEntity: LoanRequestEntity): LoanRequest {

        return LoanRequest(
            amount = loanRequestEntity.amount,
            firstName = loanRequestEntity.firstName,
            lastName = loanRequestEntity.lastName,
            percent = loanRequestEntity.percent,
            period = loanRequestEntity.period,
            phoneNumber = loanRequestEntity.phoneNumber
        )
    }
}