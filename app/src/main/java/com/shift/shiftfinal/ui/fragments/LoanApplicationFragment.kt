package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.App
import com.shift.shiftfinal.databinding.FragmentLoanApplicationBinding
import com.shift.shiftfinal.domain.entity.LoanApplicationEntity
import com.shift.shiftfinal.presentation.state.LoanApplicationScreenState
import com.shift.shiftfinal.presentation.viewmodels.LoanApplicationViewModel
import javax.inject.Inject

private const val AMOUNT = "AMOUNT"
private const val PERCENT = "PERCENT"
private const val PERIOD = "PERIOD"
private var Bundle.loanApplication
    get() = LoanApplicationEntity(
        amount = getInt(AMOUNT),
        percent = getDouble(PERCENT),
        period = getInt(PERIOD),
    )
    set(value) {
        putInt(AMOUNT, value.amount)
        putDouble(PERCENT, value.percent)
        putInt(PERIOD, value.period)
    }

class LoanApplicationFragment : Fragment() {

    companion object {

        fun newInstance(loanApplication: LoanApplicationEntity): Fragment =
            LoanApplicationFragment().apply {
                arguments = Bundle().apply { this.loanApplication = loanApplication }
            }
    }

    @Inject
    lateinit var loanApplicationViewModelFactory: LoanApplicationViewModel.Factory
    private val viewModel: LoanApplicationViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return loanApplicationViewModelFactory.create(requireArguments().loanApplication) as T
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

        setListeners()

        viewModel.state.observe(viewLifecycleOwner, ::observeState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {

        with(binding) {
            topAppBar.setNavigationOnClickListener { viewModel.back() }
            btnApplyLoan.setOnClickListener { viewModel.sendLoanApplication() }
            loanNameEditText.doAfterTextChanged { viewModel.setName(it.toString()) }
            loanSecondNameEditText.doAfterTextChanged { viewModel.setSecondName(it.toString()) }
            loanPhoneEditText.doAfterTextChanged { viewModel.setPhoneNumber(it.toString()) }
        }

    }

    private fun observeState(state: LoanApplicationScreenState) {
        when (state) {
            LoanApplicationScreenState.Loading -> {
                binding.btnApplyLoan.isEnabled = false
            }

            is LoanApplicationScreenState.Content -> {
                with(binding) {
                    btnApplyLoan.isEnabled = state.isValid

                    state.name.let {
                        if (!it.edited) {
                            loanNameEditText.setText(it.value)
                        }
                        loanName.error = it.error?.let { getString(it) }
                    }

                    state.secondName.let {
                        if (!it.edited) {
                            loanSecondNameEditText.setText(it.value)
                        }
                        loanSecondName.error = it.error?.let { getString(it) }
                    }
                    state.phone.let {
                        if (!it.edited) {
                            loanPhoneEditText.setText(it.value)
                        }
                    }

                }
            }

        }
    }
}