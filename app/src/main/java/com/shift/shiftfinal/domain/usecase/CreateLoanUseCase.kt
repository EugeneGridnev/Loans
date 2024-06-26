package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.data.model.LoanRequest
import com.shift.shiftfinal.domain.entity.LoanRequestEntity
import com.shift.shiftfinal.domain.repository.LoanRepository
import javax.inject.Inject

class CreateLoanUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    suspend operator fun invoke(loanRequest: LoanRequestEntity) = loanRepository.createLoan(loanRequest)
}