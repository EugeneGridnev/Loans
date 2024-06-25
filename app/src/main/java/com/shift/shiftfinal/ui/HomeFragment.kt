package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.data.model.LoanCondition
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.databinding.FragmentOnboardingFirstStepBinding
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.presentation.viewmodels.HomeViewModel
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment
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

class HomeFragment : Fragment() {

    companion object {

        fun newInstance(loanCondition: LoanConditionEntity): Fragment = HomeFragment().apply {
            arguments = Bundle().apply { this.loanCondition = loanCondition }
        }
    }

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory
    private val viewModel: HomeViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return homeViewModelFactory.create(arguments!!.loanCondition) as T
            }
        }
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
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

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.info -> {
                    viewModel.openOnboarding(requireArguments().loanCondition)
                    true
                }

                else -> false
            }
        }

        binding.btnLoanApplication.setOnClickListener {
            viewModel.openLoanApplication(requireArguments().loanCondition)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}