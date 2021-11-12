package com.example.orenburjie.adapters.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.orenburjie.BR

abstract class RecyclerViewAdapterWithBinding<T: RecyclerViewAdapterWithBinding.Holder>(): RecyclerView.Adapter<T>() {

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return getHolder(binding)
    }

    final override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(getItemByPosition(position))
    }

    final override fun getItemViewType(position: Int): Int = getLayoutResId(position)

    abstract fun getLayoutResId(position:Int): Int

    abstract override fun getItemCount(): Int

    abstract fun getHolder(binding: ViewDataBinding): T

    abstract fun getItemByPosition(position: Int): Any

    open class Holder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){

        open fun bindHandler(handler: Any){}

        fun bind(item: Any){
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }

    }

}