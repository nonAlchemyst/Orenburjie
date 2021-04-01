package com.example.orenburjie.priroda.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.orenburjie.Item
import com.example.orenburjie.R
import com.example.orenburjie.priroda.adapters.RestingPlacesListAdapter
import com.example.orenburjie.priroda.interfaces.OnTransferItem
import com.example.orenburjie.priroda.objects.RestingPlace

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrirodaItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrirodaItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val list = view.findViewById<ListView>(R.id.restingPlaces_list)
        list.adapter = RestingPlacesListAdapter(R.layout.rp_list_item, restingPlaces!!)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrirodaItemFragment.
         */
        // TODO: Rename and change types and number of parameters
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