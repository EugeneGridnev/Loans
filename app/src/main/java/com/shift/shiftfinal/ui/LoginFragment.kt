package com.shift.shiftfinal.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentLoginBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.state.LoginScreenState
import com.shift.shiftfinal.presentation.viewmodels.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEnter.setOnClickListener {
                viewModel.startLogin()
            }

            btnRegister.setOnClickListener {
                viewModel.startRegistration()
            }

            btnIn.setOnClickListener {
                viewModel.finishAuthenticate()
            }

            viewModel.state.observe(viewLifecycleOwner, ::observeState)

            loginEditText.doAfterTextChanged { viewModel.setLogin(it.toString()) }
            passwordEditText.doAfterTextChanged { viewModel.setPassword(it.toString()) }
            repeatPasswordEditText.doAfterTextChanged { viewModel.setRepeatPassword(it.toString()) }
        }
    }

    private fun observeState(state: LoginScreenState) {
        when (state) {
            LoginScreenState.Loading -> {

            }

            is LoginScreenState.LoginContent -> {
                with(binding) {
                    btnIn.isEnabled = state.isValid
                    repeatPassword.isVisible = false
                    btnIn.text = getString(R.string.login_button_text)
                    btnEnter.setTextColor(Color.parseColor("#DEB800"))
                    btnRegister.setTextColor(Color.parseColor("#73787F"))

                    state.error?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }

                    state.login.let {
                        if (!it.edited) {
                            loginEditText.setText(it.value)
                        }
                        login.error = it.error?.let { getString(it) }
                    }

                    state.password.let {
                        if (!it.edited) {
                            passwordEditText.setText(it.value)
                        }
                        password.error = it.error?.let { getString(it) }
                    }

                }
            }

            is LoginScreenState.RegistrationContent -> {
                with(binding) {
                    btnIn.isEnabled = state.isValid
                    repeatPassword.isVisible = true
                    btnIn.text = getString(R.string.register_button_text)
                    btnEnter.setTextColor(Color.parseColor("#73787F"))
                    btnRegister.setTextColor(Color.parseColor("#DEB800"))

                    state.error?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }

                    state.login.let {
                        if (!it.edited) {
                            loginEditText.setText(it.value)
                        }
                        login.error = it.error?.let { getString(it) }
                    }

                    state.password.let {
                        if (!it.edited) {
                            passwordEditText.setText(it.value)
                        }
                        password.error = it.error?.let { getString(it) }
                    }

                    state.repeatPassword.let {
                        if (!it.edited) {
                            repeatPasswordEditText.setText(it.value)
                        }
                        repeatPassword.error = it.error?.let { getString(it) }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}