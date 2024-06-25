package com.shift.shiftfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shift.shiftfinal.databinding.FragmentLoanAcceptedBinding
import com.shift.shiftfinal.databinding.FragmentLoanDeniedBinding

private const val LOAN_AMOUNT = "LOAN_AMOUNT"
private var Bundle.loanAmount
    get() = getInt(LOAN_AMOUNT)
    set(value) = putInt(LOAN_AMOUNT, value)

class LoanAcceptedFragment : Fragment() {

    companion object {

        fun newInstance(loanAmount: Int): Fragment = LoanDetailsFragment().apply {
            arguments = Bundle().apply { this.loanAmount = loanAmount }
        }
    }

    private var _binding: FragmentLoanAcceptedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoanAcceptedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}