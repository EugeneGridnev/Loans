package com.shift.shiftfinal.data

import android.content.Context

class AuthorizationService(context: Context) {

    companion object {
        const val TOKEN_PREF = "TOKEN_PREF"
    }

    val token: String?
        get() = sharedPref.getString(TOKEN_PREF, null)

    private val sharedPref = context.getSharedPreferences(
        TOKEN_PREF, Context.MODE_PRIVATE
    )


    fun setToken(value: String?) {
        with(sharedPref.edit()) {
            putString(TOKEN_PREF, value)
            apply()
        }
    }
}