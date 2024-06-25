package com.shift.shiftfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentMenuBinding
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {

        with(binding) {
            topAppBar.setOnMenuItemClickListener { menuItem ->
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

            myLoans.setOnClickListener {

            }

            offers.setOnClickListener {

            }

            banks.setOnClickListener { }

            help.setOnClickListener { }

            exit.setOnClickListener { }
        }
    }
}