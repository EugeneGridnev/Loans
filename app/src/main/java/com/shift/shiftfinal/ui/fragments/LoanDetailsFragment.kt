package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentLoanDetailsBinding
import com.shift.shiftfinal.domain.entity.LoanState
import com.shift.shiftfinal.presentation.state.LoanDetailsScreenState
import com.shift.shiftfinal.presentation.viewmodels.LoanDetailsViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val LOAN_ID = "LOAN_ID"
private var Bundle.loanId
    get() = getInt(LOAN_ID)
    set(value) = putInt(LOAN_ID, value)

class LoanDetailsFragment : Fragment() {

    companion object {

        private val dateFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        fun newInstance(loanId: Int): Fragment = LoanDetailsFragment().apply {
            arguments = Bundle().apply { this.loanId = loanId }
        }
    }

    @Inject
    lateinit var loanDetailsViewModelFactory: LoanDetailsViewModel.Factory
    private val viewModel: LoanDetailsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return loanDetailsViewModelFactory.create(requireArguments().loanId) as T
            }
        }
    }

    private var _binding: FragmentLoanDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

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

        binding.refresh.setOnRefreshListener { viewModel.getLoanDetails() }
        binding.topAppBar.setNavigationOnClickListener { viewModel.back() }
        viewModel.state.observe(viewLifecycleOwner, ::observeState)
    }

    private fun observeState(state: LoanDetailsScreenState) {
        when (state) {
            is LoanDetailsScreenState.Content -> {
                with(binding) {

                    refresh.isRefreshing = false
                    content.isVisible = true
                    progress.isVisible = false
                    errorMessage.isVisible = false

                    topAppBar.title = "№ ${state.loanData.id}"

                    loanName.text = state.loanData.firstName
                    loanSecondName.text = state.loanData.lastName
                    loanTelephoneNumber.text = state.loanData.phoneNumber

                    loanNumber.text = state.loanData.id.toString()
                    loanPeriod.text = state.loanData.period.toString()
                    loanPercent.text = "${state.loanData.percent} %"
                    loanAmount.text = "${state.loanData.percent} ₽"
                    loanDate.text = state.loanData.date.format(dateFormater)

                    when (state.loanData.state) {
                        LoanState.APPROVED -> {
                            loanStatus.text =
                                requireContext().getString(R.string.indicator_approved_text)
                            loanStatus.setTextColor(
                                requireContext().getColor(R.color.indicator_positive)
                            )
                        }

                        LoanState.REGISTERED -> {
                            loanStatus.text =
                                requireContext().getString(R.string.inicator_registered_text)
                            loanStatus.setTextColor(
                                requireContext().getColor(R.color.indicator_attention)
                            )
                        }

                        LoanState.REJECTED -> {
                            loanStatus.text =
                                requireContext().getString(R.string.indicator_done_text)
                        }
                    }
                }
            }

            is LoanDetailsScreenState.Error -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = false
                    errorMessage.isVisible = true
                    refresh.isRefreshing = false
                }
            }

            LoanDetailsScreenState.Loading -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = true
                    errorMessage.isVisible = false
                    refresh.isRefreshing = true
                }
            }

            LoanDetailsScreenState.Initial -> viewModel.getLoanDetails()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}