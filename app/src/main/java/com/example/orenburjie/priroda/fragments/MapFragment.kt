package com.example.orenburjie.priroda.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.orenburjie.R
import com.example.orenburjie.priroda.interfaces.OnTransferItem
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), Session.SearchListener, CameraListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var Interface: OnTransferItem
    private lateinit var currentCameraPosition: Point
    private var zoom: Float = 14.0f
    private lateinit var mapView: MapView
    private lateinit var btnIncZoom: Button
    private lateinit var btnDecZoom: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        Interface = context as OnTransferItem
        var item = Interface.transferItem()
        btnIncZoom = view.findViewById(R.id.zoom)
        btnDecZoom = view.findViewById(R.id.antizoom)
        mapView = view.findViewById(R.id.map)
        mapView.map.addCameraListener(this)
        currentCameraPosition = item.toPoint()
        mapView.map.move(
                CameraPosition(currentCameraPosition, zoom, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 1f),
                null)
        btnIncZoom.setOnClickListener {
            zoom(it)
        }
        btnDecZoom.setOnClickListener {
            zoom(it)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MapFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

    }

    private fun zoom(view: View){
        when(view.id){
            R.id.zoom -> {
                zoom++
            }
            R.id.antizoom ->{
                zoom--
            }
        }
        mapView.map.move(
                CameraPosition(currentCameraPosition, zoom, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 1f),
                null)
    }


    override fun onPause() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onPause()
    }

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        super.onStart()
    }

    override fun onSearchError(p0: Error) {
        TODO("Not yet implemented")
    }

    override fun onSearchResponse(p0: Response) {
        TODO("Not yet implemented")
    }

    override fun onCameraPositionChanged(p0: Map, p1: CameraPosition, p2: CameraUpdateReason, p3: Boolean) {
        currentCameraPosition = Point(p1.target.latitude, p1.target.longitude)
    }
}