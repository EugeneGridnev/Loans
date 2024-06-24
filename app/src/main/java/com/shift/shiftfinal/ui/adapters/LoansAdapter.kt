package com.shift.shiftfinal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shift.shiftfinal.databinding.LoanListItemBinding
import com.shift.shiftfinal.domain.entity.LoanEntity
import com.shift.shiftfinal.ui.holder.LoanViewHolder

class LoansAdapter(private val onItemClickListener: ((LoanEntity) -> Unit)) :
    RecyclerView.Adapter<LoanViewHolder>() {

    var loans: List<LoanEntity> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        return LoanViewHolder(
            LoanListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val loan = loans[position]
        holder.bind(loan)
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(loan)
        }
    }

    override fun getItemCount(): Int = loans.size

}