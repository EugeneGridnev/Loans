package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.state.HomeScreenState
import com.shift.shiftfinal.presentation.viewmodels.HomeViewModel
import com.shift.shiftfinal.ui.adapters.LoansAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var loansAdapter: LoansAdapter

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

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

        with(binding) {
            topAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.info -> {
                        viewModel.openOnboarding()
                        true
                    }

                    else -> false
                }
            }

            btnLoanApplication.setOnClickListener {
                viewModel.openLoanApplication()
            }

            loanSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    viewModel.setAmount(progress.toString())
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    return
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    return
                }

            })

            allLoansButton.setOnClickListener { viewModel.openMyLoans() }

            refresh.setOnRefreshListener { viewModel.loadData() }

            loanAmountEdit.doAfterTextChanged { viewModel.setAmount(it.toString()) }
        }

        setUpLoansRecycler()
        viewModel.state.observe(viewLifecycleOwner, ::observeState)
    }

    private fun observeState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.Content -> {
                with(binding) {
                    content.isVisible = true
                    progress.isVisible = false
                    error.isVisible = false
                    refresh.isRefreshing = false

                    loanSlider.max = state.loanHomeConditionEntity.maxAmount
                    loanSlider.min = state.loanHomeConditionEntity.minAmount
                    loanSlider.progress = state.loanHomeConditionEntity.amount
                    loanAmountEdit.setText(state.loanHomeConditionEntity.amount.toString())
                    loanMin.text = state.loanHomeConditionEntity.minAmount.toString()
                    loanMax.text = state.loanHomeConditionEntity.maxAmount.toString()
                    loanConditions.text =
                        "Под ${state.loanHomeConditionEntity.percent}% на ${state.loanHomeConditionEntity.period} дней"
                    if (state.loanHomeConditionEntity.errorStatus != "") {
                        errorMessage.text = state.loanHomeConditionEntity.errorStatus
                        errorMessage.isVisible = true
                        btnLoanApplication.isEnabled = false
                    } else {
                        errorMessage.isVisible = false
                        btnLoanApplication.isEnabled = true
                    }

                    state.loanList.let {

                        if (it.isEmpty()) {
                            loanList.isVisible = false
                            myLoansTextStub.isVisible = true
                            return@let
                        }

                        loanList.isVisible = true
                        myLoansTextStub.isVisible = false
                        loansAdapter.loans = it.take(3)

                    }
                }
            }

            is HomeScreenState.Error -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = false
                    error.isVisible = true
                    refresh.isRefreshing = false
                }
            }

            HomeScreenState.Initial -> {
                viewModel.loadData()
            }

            HomeScreenState.Loading -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = true
                    error.isVisible = false
                    refresh.isRefreshing = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpLoansRecycler() {
        loansAdapter = LoansAdapter(
            onItemClickListener = { onLoanClicked(it) }
        )
        binding.homeLoanRecycler.adapter = loansAdapter
    }

    private fun onLoanClicked(loanEntity: LoanEntity) {
        viewModel.openLoanInfo(loanEntity.id)
    }
}