package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.repository.AuthRepository
import javax.inject.Inject

class CheckUserLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.isLoggedIn
}