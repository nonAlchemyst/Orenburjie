package com.example.orenburjie.priroda.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.orenburjie.Item
import com.example.orenburjie.R
import com.example.orenburjie.priroda.adapters.ImagesAdapter
import com.example.orenburjie.priroda.interfaces.OnTransferItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrirodaMainItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrirodaMainItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var Interface: OnTransferItem
    private lateinit var item: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)
        Interface = context as OnTransferItem
        item = Interface.transferItem()
        val title = view.findViewById<TextView>(R.id.description_title)
        val description = view.findViewById<TextView>(R.id.description_info)
        val images = view.findViewById<RecyclerView>(R.id.description_images)
        title.text = item.title
        description.text = item.description
        val manager = LinearLayoutManager(context)
        manager.orientation = RecyclerView.HORIZONTAL
        images.layoutManager = manager
        val adapter = ImagesAdapter(requireContext(), R.layout.priroda_item_images_item, item.images!!)
        images.adapter = adapter
        images.visibility
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(images)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrirodaMainItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PrirodaMainItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}