package com.example.orenburjie.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_main_screen.*
import com.example.orenburjie.R
import com.example.orenburjie.Router
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.viewmodels.MainScreenViewModel

class MainScreenFragment: BaseFragment<MainScreenViewModel>(MainScreenViewModel::class.java, R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestWriteExternalStoragePermission()
        requestReadExternalStoragePermission()
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
    }

    private fun requestWriteExternalStoragePermission(): Boolean{
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 3)
        }else{
            //Сработает если разрешение уже есть
            return true
        }
        return false
    }

    private fun requestReadExternalStoragePermission(): Boolean{
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }else{
            //Сработает если разрешение уже есть
            return true
        }
        return false
    }
}