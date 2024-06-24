package com.shift.shiftfinal.presentation.navigation

import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(
    private val router: Router
) : LoginRouter {
}