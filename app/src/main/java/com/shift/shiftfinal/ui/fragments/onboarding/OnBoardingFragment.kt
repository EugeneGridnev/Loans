package com.shift.shiftfinal.ui.fragments.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentOnBoardingBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.viewmodels.OnBoardingViewModel
import com.shift.shiftfinal.ui.adapters.OnBoardingAdapter
import javax.inject.Inject

class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingStepsAdapter: OnBoardingAdapter

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: OnBoardingViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            viewModel.openMain()
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
                            viewModel.openMain()
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

