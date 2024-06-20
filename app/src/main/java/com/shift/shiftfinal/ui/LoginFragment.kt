package com.shift.shiftfinal.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnter.setOnClickListener {
            with(binding) {
                repeatPassword.isVisible = false
                btnIn.text = "Войти"
                btnEnter.setTextColor(Color.parseColor("#DEB800"))
                btnRegister.setTextColor(Color.parseColor("#73787F"))
            }
        }

        binding.btnRegister.setOnClickListener {
            with(binding) {
                repeatPassword.isVisible = true
                btnIn.text = "Зарегистрироваться"
                btnEnter.setTextColor(Color.parseColor("#73787F"))
                btnRegister.setTextColor(Color.parseColor("#DEB800"))
            }
        }

        binding.btnIn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, OnBoardingFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}