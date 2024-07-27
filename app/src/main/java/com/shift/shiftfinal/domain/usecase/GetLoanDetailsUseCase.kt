package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanDetailsUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    suspend operator fun invoke(loanId: Int) = loanRepository.getLoanDetails(loanId)
}