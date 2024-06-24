package com.shift.shiftfinal.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.shift.shiftfinal.databinding.LoanListItemBinding
import com.shift.shiftfinal.domain.entity.LoanEntity

class LoanViewHolder (
    private val binding: LoanListItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(loanEntity: LoanEntity) {

        with(binding) {
            loanNumber.text = "№ ${loanEntity.id}"
            loanValue.text = "${loanEntity.amount} ₽"
        }
    }
}