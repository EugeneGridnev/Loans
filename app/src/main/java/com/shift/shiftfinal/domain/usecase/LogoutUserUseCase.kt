package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.logout()
}