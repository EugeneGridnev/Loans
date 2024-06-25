package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.App
import com.shift.shiftfinal.databinding.FragmentLoanAcceptedBinding
import com.shift.shiftfinal.presentation.viewmodels.LoanAcceptedViewModel
import javax.inject.Inject

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

    @Inject
    lateinit var loanAcceptedViewModelFactory: LoanAcceptedViewModel.Factory
    private val viewModel: LoanAcceptedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return loanAcceptedViewModelFactory.create(requireArguments().loanAmount) as T
            }
        }
    }

    private var _binding: FragmentLoanAcceptedBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

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