package com.shift.shiftfinal.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.databinding.FragmentSpecialOfferBinding
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.viewmodels.SpecialOfferViewModel
import javax.inject.Inject

class SpecialOfferFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SpecialOfferViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentSpecialOfferBinding? = null
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
        _binding = FragmentSpecialOfferBinding.inflate(inflater, container, false)
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
            topAppBar.setNavigationOnClickListener {
                viewModel.backToMain()
            }
            btnSpecialOffer.setOnClickListener { viewModel.openBanksStub() }
        }
    }
}