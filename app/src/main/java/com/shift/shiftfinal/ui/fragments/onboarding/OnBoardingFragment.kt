package com.shift.shiftfinal.ui.fragments.onboarding

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentOnBoardingBinding
import com.shift.shiftfinal.ui.LoginFragment
import com.shift.shiftfinal.ui.MainFragment
import com.shift.shiftfinal.ui.adapters.OnBoardingAdapter


class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingStepsAdapter: OnBoardingAdapter

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment())
                .commit()
        }
        setUpOnboardingViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpOnboardingViewPager() {

        onBoardingStepsAdapter = OnBoardingAdapter(
            childFragmentManager,
            lifecycle
        )

        binding.onBoardingViewPager.apply {
            adapter = onBoardingStepsAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    changeStepState(context)
                    super.onPageSelected(position)
                }
            })
        }
    }

    private fun changeStepState(context: Context) {
        with(binding) {
            when (onBoardingViewPager.currentItem) {
                0 -> {
                    with(binding) {
                        backButton.isVisible = false
                        nextButton.text = "Далее"
                        nextButton.setOnClickListener {
                            if (onBoardingViewPager.currentItem < onBoardingViewPager.adapter!!.itemCount - 1) {
                                onBoardingViewPager.currentItem += 1
                            }
                        }
                        stepOne.background = context.getDrawable(R.color.icon_primary)
                        stepTwo.background = context.getDrawable(R.color.bg_disable)
                        stepThree.background = context.getDrawable(R.color.bg_disable)
                    }
                }

                1 -> {
                    with(binding) {
                        backButton.isVisible = true
                        backButton.setOnClickListener {
                            if (onBoardingViewPager.currentItem > 0) {
                                onBoardingViewPager.currentItem -= 1
                            }
                        }
                        nextButton.text = "Далее"
                        nextButton.setOnClickListener {
                            if (onBoardingViewPager.currentItem < onBoardingViewPager.adapter!!.itemCount - 1) {
                                onBoardingViewPager.currentItem += 1
                            }
                        }
                        stepOne.background = context.getDrawable(R.color.bg_disable)
                        stepTwo.background = context.getDrawable(R.color.icon_primary)
                        stepThree.background = context.getDrawable(R.color.bg_disable)
                    }

                }

                2 -> {
                    with(binding) {
                        backButton.isVisible = true
                        nextButton.text = "Закрыть"
                        backButton.setOnClickListener {
                            if (onBoardingViewPager.currentItem > 0) {
                                onBoardingViewPager.currentItem -= 1
                            }
                        }
                        nextButton.setOnClickListener {
                            parentFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, MainFragment())
                                .commit()
                        }
                        stepOne.background = context.getDrawable(R.color.bg_disable)
                        stepTwo.background = context.getDrawable(R.color.bg_disable)
                        stepThree.background = context.getDrawable(R.color.icon_primary)
                    }
                }

                else -> {}
            }
        }

    }

}

