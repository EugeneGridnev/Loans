package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanConditionUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    suspend operator fun invoke() = loanRepository.getLoanCondition()
}