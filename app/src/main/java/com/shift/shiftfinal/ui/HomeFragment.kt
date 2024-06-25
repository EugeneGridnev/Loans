package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentHomeBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
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

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.info -> {
                    viewModel.openOnboarding()
                    true
                }

                else -> false
            }
        }

        binding.btnLoanApplication.setOnClickListener {
            //viewModel.openLoanApplication()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}