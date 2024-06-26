package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentMenuBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.viewmodels.MenuViewModel
import javax.inject.Inject

class MenuFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MenuViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMenuBinding? = null
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
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {

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

            myLoans.setOnClickListener {
                viewModel.openMyLoans()
            }

            offers.setOnClickListener {
                viewModel.openSpecialOffer()
            }

            banks.setOnClickListener {
                viewModel.openBanksStub()
            }

            help.setOnClickListener {
                viewModel.openHelp()
            }

            exit.setOnClickListener {

            }
        }
    }
}