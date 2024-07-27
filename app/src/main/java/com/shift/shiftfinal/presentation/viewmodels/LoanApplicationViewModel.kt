package com.shift.shiftfinal.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shift.shiftfinal.R
import com.shift.shiftfinal.domain.entity.LoanApplicationEntity
import com.shift.shiftfinal.domain.entity.LoanRequestEntity
import com.shift.shiftfinal.domain.entity.LoanState
import com.shift.shiftfinal.domain.exceptions.ApiException
import com.shift.shiftfinal.domain.exceptions.AuthException
import com.shift.shiftfinal.domain.usecase.CreateLoanUseCase
import com.shift.shiftfinal.domain.usecase.LogoutUserUseCase
import com.shift.shiftfinal.navigation.ActivityRouter
import com.shift.shiftfinal.navigation.MainFragmentRouter
import com.shift.shiftfinal.presentation.state.Field
import com.shift.shiftfinal.presentation.state.LoanApplicationScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class LoanApplicationViewModel @AssistedInject constructor(
    @Assisted private val loanApplication: LoanApplicationEntity,
    private val activityRouter: ActivityRouter,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val mainFragmentRouter: MainFragmentRouter,
    private val createLoanUseCase: CreateLoanUseCase
) : ViewModel() {

    companion object {
        private val cyrillicLettersRegex = Regex("\\p{IsCyrillic}*")
    }

    @AssistedFactory
    interface Factory {
        fun create(loanApplication: LoanApplicationEntity): LoanApplicationViewModel
    }

    private val _state =
        MutableLiveData<LoanApplicationScreenState>(
            LoanApplicationScreenState.Content(
                Field(""),
                Field(""),
                Field("")
            )
        )
    val state: LiveData<LoanApplicationScreenState> = _state

    fun setName(value: String) {
        _state.value?.let {
            if (it is LoanApplicationScreenState.Content) {
                val errorRes = when {
                    !cyrillicLettersRegex.matches(value) -> R.string.only_russian_letters
                    else -> null
                }
                _state.value = it.copy(
                    name = Field(value, true, errorRes)
                )
            }
        }
    }

    fun setSecondName(value: String) {
        _state.value?.let {
            if (it is LoanApplicationScreenState.Content) {
                val errorRes = when {
                    !cyrillicLettersRegex.matches(value) -> R.string.only_russian_letters
                    else -> null
                }
                _state.value = it.copy(
                    secondName = Field(value, true, errorRes)
                )
            }
        }
    }

    fun setPhoneNumber(value: String) {
        _state.value?.let {
            if (it is LoanApplicationScreenState.Content) {
                _state.value = it.copy(
                    phone = Field(value, true)
                )
            }
        }
    }

    fun sendLoanApplication() {
        viewModelScope.launch {
            val previousState = _state.value

            if (previousState !is LoanApplicationScreenState.Content) {
                return@launch
            }

            _state.value = LoanApplicationScreenState.Loading
            try {
                val loan = createLoanUseCase(
                    LoanRequestEntity(
                        amount = loanApplication.amount,
                        firstName = previousState.name.value,
                        lastName = previousState.secondName.value,
                        percent = loanApplication.percent,
                        period = loanApplication.period,
                        phoneNumber = previousState.phone.value
                    )
                )

                when (loan.state) {
                    LoanState.APPROVED, LoanState.REGISTERED -> {
                        openLoanAccepted(loan.amount)
                        return@launch
                    }

                    LoanState.REJECTED -> openLoanDenied()
                }

            } catch (e: AuthException) {
                logoutUserUseCase()
                openLoginScreen()

            } catch (_: ApiException) {
                openLoanDenied()
            }
        }
    }

    private fun openLoanAccepted(loanAmount: Int) {
        activityRouter.openLoanAcceptedScreen(loanAmount)
    }

    private fun openLoanDenied() {
        activityRouter.openLoanDeniedScreen()
    }

    private fun openLoginScreen() {
        activityRouter.openLoginScreen()
    }

    fun back() {
        mainFragmentRouter.back()
    }
}