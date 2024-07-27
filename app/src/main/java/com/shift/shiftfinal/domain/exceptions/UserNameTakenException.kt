package com.shift.shiftfinal.domain.exceptions

class UserNameTakenException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause) {
}