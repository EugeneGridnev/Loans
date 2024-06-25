package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentMainBinding
import com.shift.shiftfinal.domain.entity.LoanConditionEntity
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFragment
import com.shift.shiftfinal.ui.screens.getHomeScreen
import com.shift.shiftfinal.ui.screens.getMainScreen
import com.shift.shiftfinal.ui.screens.getMenuScreen
import javax.inject.Inject
import javax.inject.Named

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

class MainFragment : Fragment() {

    companion object {

        fun newInstance(loanCondition: LoanConditionEntity): Fragment = MainFragment().apply {
            arguments = Bundle().apply { this.loanCondition = loanCondition }
        }
    }

    @Inject
    @Named("MainFragmentRouter")
    lateinit var router: Router
    @Inject
    @Named("MainFragmentHolder")
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: AppNavigator

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        navigator = AppNavigator(requireActivity(), R.id.mainFragmentContainer)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            router.newRootScreen(getHomeScreen(requireArguments().loanCondition))
        }

        with(binding) {

            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> {
                        router.replaceScreen(getHomeScreen(requireArguments().loanCondition))
                        true
                    }

                    R.id.menu -> {
                        router.replaceScreen(getMenuScreen())
                        true
                    }

                    else -> false
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}