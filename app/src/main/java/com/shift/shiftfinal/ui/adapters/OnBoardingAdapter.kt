package com.shift.shiftfinal.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingFirstStepFragment
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingSecondStepFragment
import com.shift.shiftfinal.ui.fragments.onboarding.OnBoardingThirdStepFragment

class OnBoardingAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val onBoardingStepsList = arrayListOf(
        OnBoardingFirstStepFragment(),
        OnBoardingSecondStepFragment(),
        OnBoardingThirdStepFragment()
    )

    override fun getItemCount(): Int {
        return onBoardingStepsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return onBoardingStepsList[position]
    }
}