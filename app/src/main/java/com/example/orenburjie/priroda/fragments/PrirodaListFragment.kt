package com.example.orenburjie.priroda.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.example.orenburjie.AdapterList
import com.example.orenburjie.Item
import com.example.orenburjie.R
import com.example.orenburjie.priroda.PrirodaItemActivity
import com.example.orenburjie.priroda.interfaces.OnTransferReference
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var items = ArrayList<Item>()
    private var list: ListView? = null
    private lateinit var Interface: OnTransferReference
    private lateinit var listListener: ValueEventListener
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_priroda_list, container, false)
        Interface = context as OnTransferReference
        ref =  Interface.getReference(R.id.listFragment)!!
        list = view.findViewById(R.id.prirodaList)
        listListener = ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    items.clear()
                    for(item in snapshot.children){
                        try {
                            items.add(item.getValue(Item::class.java)!!)
                        }catch (e: Exception){
                            Toast.makeText(context, "Получен неверный формат данных", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if(context != null) {
                        list?.adapter = AdapterList(context!!, R.layout.nature_list_item, items)
                        list?.setOnItemClickListener { parent, view, position, id ->
                            var bundle = Bundle()
                            bundle.putSerializable("Item", items[position])
                            var intent = Intent(context, PrirodaItemActivity::class.java)
                            intent.putExtras(bundle)
                            context!!.startActivity(intent)
                        }
                    }
                }
            }
        })

        return view
    }

    override fun onStop() {
        super.onStop()
        ref.removeEventListener(listListener)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}