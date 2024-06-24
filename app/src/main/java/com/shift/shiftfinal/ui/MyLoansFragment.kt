package com.shift.shiftfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shift.shiftfinal.databinding.FragmentMyLoansBinding
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.ui.adapters.LoansAdapter

class MyLoansFragment : Fragment() {
    private var _binding: FragmentMyLoansBinding? = null
    private val binding get() = _binding!!

    private lateinit var loansAdapter: LoansAdapter

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
        //viewModel.openLoanInfo(loanEntity.id)
    }
}