package com.shift.shiftfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.databinding.FragmentOnboardingFirstStepBinding
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.info -> {
                    parentFragment?.parentFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, OnBoardingFragment())
                        ?.commit()
                    true
                }

                else -> false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}