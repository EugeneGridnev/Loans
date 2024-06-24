package com.shift.shiftfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shift.shiftfinal.R
import com.shift.shiftfinal.data.model.LoanCondition
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.databinding.FragmentOnboardingFirstStepBinding
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment

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

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
                    parentFragment?.parentFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, OnBoardingFragment())
                        ?.commit()
                    true
                }

                else -> false
            }
        }

        binding.btnLoanApplication.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.mainFragmentContainer, LoanApplicationFragment())
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}