package com.example.orenburjie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.orenburjie.priroda.adapters.RestingPlacesListAdapter
import com.example.orenburjie.priroda.interfaces.OnTransferItem
import com.example.orenburjie.priroda.objects.RestingPlace

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PrirodaItemFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var Interface: OnTransferItem
    private var restingPlaces: ArrayList<RestingPlace>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_resting_places, container, false)
        Interface = context as OnTransferItem
        restingPlaces = Interface.transferItem().toRestingPlaces()
        val list = view.findViewById<ListView>(R.id.resting_places_list)
        if(restingPlaces != null)
            list.adapter = RestingPlacesListAdapter(R.layout.rp_list_item, restingPlaces!!)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PrirodaItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}