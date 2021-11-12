package com.example.orenburjie.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.orenburjie.BR
import com.example.orenburjie.Router
import com.example.orenburjie.interfaces.OnBackPressed

open class BaseFragment<T: ViewModel>(private val viewModelClass: Class<T>, private val layoutSrc: Int): Fragment() {

    private val onSystemBackPressedListener = object: OnBackPressed{
        override fun onBackPressed() {
            onBack()
        }
    }

    protected lateinit var viewModel: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Router.setSystemBackPressedListener(onSystemBackPressedListener)
        viewModel = ViewModelProvider(this).get(viewModelClass)
        val view = inflater.inflate(layoutSrc, container, false)
        return view
    }

    open fun onBack(){
        //Default logic
        Router.getInstance().back()
    }
}