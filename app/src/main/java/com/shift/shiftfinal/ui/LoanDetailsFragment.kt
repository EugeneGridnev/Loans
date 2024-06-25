package com.shift.shiftfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shift.shiftfinal.databinding.FragmentLoanDetailsBinding

private const val LOAN_ID = "LOAN_ID"
private var Bundle.loan_id
    get() = getInt(LOAN_ID)
    set(value) = putInt(LOAN_ID, value)

class LoanDetailsFragment : Fragment() {

    companion object {

        fun newInstance(loanId: Int): Fragment = LoanDetailsFragment().apply {
            arguments = Bundle().apply { this.loan_id = loanId }
        }
    }

    private var _binding: FragmentLoanDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoanDetailsBinding.inflate(inflater, container, false)
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