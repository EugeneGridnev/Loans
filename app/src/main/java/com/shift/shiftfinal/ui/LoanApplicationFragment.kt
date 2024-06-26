package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.App
import com.shift.shiftfinal.databinding.FragmentLoanApplicationBinding
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.presentation.viewmodels.LoanApplicationViewModel
import javax.inject.Inject

private const val MAX_AMOUNT = "MAX_AMOUNT"
private const val PERCENT = "PERCENT"
private const val PERIOD = "PERIOD"
private var Bundle.loanCondition
    get() = LoanConditionEntity(
        maxAmount = getInt(MAX_AMOUNT),
        percent = getDouble(PERCENT),
        period = getInt(PERIOD),
    )
    set(value) {
        putInt(MAX_AMOUNT, value.maxAmount)
        putDouble(PERCENT, value.percent)
        putInt(PERIOD, value.period)
    }

class LoanApplicationFragment : Fragment() {

    companion object {

        fun newInstance(loanCondition: LoanConditionEntity): Fragment =
            LoanApplicationFragment().apply {
                arguments = Bundle().apply { this.loanCondition = loanCondition }
            }
    }

    @Inject
    lateinit var loanApplicationViewModelFactory: LoanApplicationViewModel.Factory
    private val viewModel: LoanApplicationViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return loanApplicationViewModelFactory.create(requireArguments().loanCondition) as T
            }
        }
    }

    private var _binding: FragmentLoanApplicationBinding? = null
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
        _binding = FragmentLoanApplicationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            viewModel.back()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}