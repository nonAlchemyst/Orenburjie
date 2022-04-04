package com.example.orenburjie.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_main_screen.*
import com.example.orenburjie.R
import com.example.orenburjie.Router
import com.example.orenburjie.Utils.StringUtils
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.viewmodels.MainScreenViewModel
import kotlinx.android.synthetic.main.layout_menu_toolbar.*

class MainScreenFragment: BaseFragment<MainScreenViewModel>(MainScreenViewModel::class.java, R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nature.setOnClickListener {
            viewModel.naturePressed()
        }
        culture.setOnClickListener {
            viewModel.culturePressed()
        }
        excursions.setOnClickListener {
            viewModel.excursionPressed()
        }
        developer_button.setOnClickListener {
            viewModel.developerPressed()
        }
        //txtToolbar.isSelected = true
        //txtToolbar.text = StringUtils.getString(R.string.app_name)
    }
}