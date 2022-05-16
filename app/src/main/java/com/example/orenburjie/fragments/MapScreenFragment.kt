package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.objects.Item
import com.example.orenburjie.viewmodels.MapScreenViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

class MapScreenFragment(private val item: Item): BaseFragment<MapScreenViewModel>(MapScreenViewModel::class.java, R.layout.fragment_map),
    CameraListener {

    private lateinit var cameraPos: CameraPosition
    private val defaultZoom = 14f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setItem(item)
        nav_bar_wirh_title_title.text = viewModel.getTitle()
        nav_bar_with_title_back.setOnClickListener {
            viewModel.back()
        }
        cameraPos = CameraPosition(viewModel.getLocation(), defaultZoom, 0f, 0f)
        map.map.addCameraListener(this)
        mapMove(cameraPos)
        map.map.mapObjects.addPlacemark(viewModel.getLocation(), ImageProvider.fromResource(requireContext(), R.drawable.place_mark_ic), IconStyle(
            null, null, null, true, true, 0.8f, null
        ))
    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        p3: Boolean,
    ) {
        cameraPos = p1
        //mapMove(cameraPos)
    }

    override fun onStop() {
        super.onStop()
        map.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        map.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onBack() {
        viewModel.back()
    }

    private fun mapMove(cameraPosition: CameraPosition){
        map?.apply{
            map.move(
                cameraPosition,
                Animation(Animation.Type.SMOOTH, 1f),
                null)
        }
    }
}