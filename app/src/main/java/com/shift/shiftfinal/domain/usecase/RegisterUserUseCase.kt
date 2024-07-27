package com.shift.shiftfinal.domain.usecase

import com.shift.shiftfinal.domain.entity.AuthEntity
import com.shift.shiftfinal.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(authEntity: AuthEntity) = authRepository.register(authEntity)
}