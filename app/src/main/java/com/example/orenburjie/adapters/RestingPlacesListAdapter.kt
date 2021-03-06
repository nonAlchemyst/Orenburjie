package com.example.orenburjie.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.orenburjie.R
import com.example.orenburjie.objects.RestingPlace

class RestingPlacesListAdapter(private val resource: Int, private val list: List<RestingPlace>): BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent!!.context).inflate(resource, parent, false)
        val name = view.findViewById<TextView>(R.id.rp_list_item_name)
        val phone = view.findViewById<TextView>(R.id.rp_list_item_phone)
        val street = view.findViewById<TextView>(R.id.rp_list_item_street)
        name.text = getItem(position).name
        phone.text = getItem(position).phone
        street.text = getItem(position).street
        /*view.setOnClickListener {
            try {
                parent!!.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getItem(position).pathToSite)))
            }catch(E: Exception){
                Toast.makeText(parent!!.context, "Неправильная ссылка", Toast.LENGTH_SHORT).show()
            }
        }*/
        //Временно или не временно отключено
        return view
    }

    override fun getItem(position: Int): RestingPlace {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}