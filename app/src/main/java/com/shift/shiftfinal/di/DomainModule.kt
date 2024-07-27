package com.shift.shiftfinal.di

import com.shift.shiftfinal.data.repositories.AuthRepositoryImpl
import com.shift.shiftfinal.data.repositories.LoanRepositoryImpl
import com.shift.shiftfinal.domain.repository.AuthRepository
import com.shift.shiftfinal.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun provideAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun provideLoanRepository(repositoryImpl: LoanRepositoryImpl): LoanRepository
}