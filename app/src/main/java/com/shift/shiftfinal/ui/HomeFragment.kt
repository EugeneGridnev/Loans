package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.state.HomeScreenState
import com.shift.shiftfinal.presentation.viewmodels.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentHomeBinding? = null
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
                    //viewModel.setAmount(progress.toString())
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    return
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    return
                }

            })

            loanAmountEdit.doAfterTextChanged { viewModel.setAmount(it.toString()) }
        }

        //viewModel.state.observe(viewLifecycleOwner, ::observeState)
    }

    private fun observeState(state: HomeScreenState) {
        when(state) {
            is HomeScreenState.Content -> {
                with(binding) {
                    content.isEnabled = true
                    progress.isEnabled = false
                    error.isEnabled = false

                    loanSlider.max = state.loanHomeConditionEntity.maxAmount
                    loanSlider.min = state.loanHomeConditionEntity.minAmount
                    loanSlider.progress = state.loanHomeConditionEntity.amount
                    loanAmountEdit.setText(state.loanHomeConditionEntity.amount.toString())
                    loanMin.text = state.loanHomeConditionEntity.minAmount.toString()
                    loanMax.text = state.loanHomeConditionEntity.maxAmount.toString()
                    loanConditions.text = "Под ${state.loanHomeConditionEntity.percent}% на ${state.loanHomeConditionEntity.period} дней"
                }
            }
            is HomeScreenState.Error -> {
                with(binding) {
                    content.isEnabled = false
                    progress.isEnabled = false
                    error.isEnabled = true
                }
            }
            HomeScreenState.Initial -> {
                viewModel.loadData()
            }
            HomeScreenState.Loading -> {
                with(binding) {
                    content.isEnabled = false
                    progress.isEnabled = true
                    error.isEnabled = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}