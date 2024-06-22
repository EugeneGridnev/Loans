package com.shift.shiftfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.shift.shiftfinal.databinding.FragmentLoanApplicationBinding

class LoanApplicationFragment : Fragment() {
    private var _binding: FragmentLoanApplicationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoanApplicationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            parentFragmentManager
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}