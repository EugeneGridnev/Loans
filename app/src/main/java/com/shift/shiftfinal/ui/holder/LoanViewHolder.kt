package com.shift.shiftfinal.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.LoanListItemBinding
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.domain.entity.LoanState

class LoanViewHolder(
    private val binding: LoanListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loanEntity: LoanEntity) {

        with(binding) {
            loanNumber.text = "№ ${loanEntity.id}"
            loanValue.text = "${loanEntity.amount} ₽"
            when(loanEntity.state) {
                LoanState.APPROVED -> {
                    loanStatus.text = itemView.context.getString(R.string.indicator_approved_text)
                    loanStatus.setTextColor(itemView.context.getColor(R.color.indicator_positive))
                }
                LoanState.REGISTERED -> {
                    loanStatus.text = itemView.context.getString(R.string.inicator_registered_text)
                    loanStatus.setTextColor(itemView.context.getColor(R.color.indicator_attention))
                }
                LoanState.REJECTED -> {
                    loanStatus.text = itemView.context.getString(R.string.indicator_done_text)
                }
            }
        }
    }
}