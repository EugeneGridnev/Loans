package com.shift.shiftfinal.presentation.state

import androidx.annotation.StringRes

data class Field<T>(
    val value: T,
    val edited: Boolean = false,
    @StringRes
    val error: Int? = null,
)