package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orenburjie.objects.Item
import com.example.orenburjie.R
import com.example.orenburjie.adapters.MenuListAdapter
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.interfaces.ICulture
import com.example.orenburjie.viewmodels.CultureViewModel
import kotlinx.android.synthetic.main.fragment_culture.*
import kotlinx.android.synthetic.main.layout_nav_bar.*

class CultureFragment: BaseFragment<CultureViewModel>(CultureViewModel::class.java, R.layout.fragment_culture) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setDataReceivingListener(object : ICulture.DataReceiving{
            override fun onSuccess() {
                culture_menu_list?.let{
                    fillList(culture_menu_list, viewModel.getItems())
                }
            }

            override fun onCancelled() {
                printErrorMessage()
            }
        })
        nav_bar_back.setOnClickListener {
            viewModel.back()
        }
    }

    override fun onBack() {
        viewModel.back()
    }

    private fun fillList(view: RecyclerView, items: List<Item>){
        val adapter = MenuListAdapter(requireContext(), items, R.layout.nature_list_item)
        val manager = LinearLayoutManager(requireContext())
        adapter.setOnMenuListItemClickListener(viewModel.onItemClickListener)
        manager.orientation = LinearLayoutManager.VERTICAL
        view.setItemViewCacheSize(10)
        view.layoutManager = manager
        view.adapter = adapter
    }

    private fun printErrorMessage(){}
}