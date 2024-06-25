package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.repository.LoanRepository
import javax.inject.Inject

class GetMyLoansUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    suspend operator fun invoke() = loanRepository.getMyLoans()
}