package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shift.shiftfinal.App
import com.shift.shiftfinal.databinding.FragmentMyLoansBinding
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.presentation.ViewModelFactory
import com.shift.shiftfinal.presentation.state.HomeScreenState
import com.shift.shiftfinal.presentation.state.MyLoansScreenState
import com.shift.shiftfinal.presentation.viewmodels.MyLoansViewModel
import com.shift.shiftfinal.ui.adapters.LoansAdapter
import javax.inject.Inject

class MyLoansFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MyLoansViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMyLoansBinding? = null
    private val binding get() = _binding!!

    private lateinit var loansAdapter: LoansAdapter

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyLoansBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLoansRecycler()
        viewModel.state.observe(viewLifecycleOwner, ::observeState)
    }

    private fun observeState(state: MyLoansScreenState) {
        when(state) {
            is MyLoansScreenState.Content -> {
                with(binding) {
                    content.isVisible = true
                    progress.isVisible = false
                    errorMessage.isVisible = false

                    loansAdapter.loans = state.loanList
                }
            }
            is MyLoansScreenState.Error -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = false
                    errorMessage.isVisible = true
                    refresh.isRefreshing = false
                }
            }

            MyLoansScreenState.Loading -> {
                with(binding) {
                    content.isVisible = false
                    progress.isVisible = true
                    errorMessage.isVisible = false
                    refresh.isRefreshing = true
                }
            }

            MyLoansScreenState.Initial -> viewModel.getMyLoans()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpLoansRecycler() {
        loansAdapter = LoansAdapter(
            onItemClickListener = { onLoanClicked(it) }
        )
        binding.myLoansRecycler.adapter = loansAdapter

    }

    private fun onLoanClicked(loanEntity: LoanEntity) {
        viewModel.openLoanDetails(loanEntity.id)
    }
}