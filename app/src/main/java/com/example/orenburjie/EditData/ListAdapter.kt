package com.example.orenburjie.EditData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.orenburjie.R

class ListAdapter(var resource: Int, var data: ArrayList<String>): BaseAdapter() {

    var childCount: ArrayList<String>? = null

    constructor(resource: Int, data: ArrayList<String>, childCount: ArrayList<String>): this(resource, data){
        this.childCount = childCount
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(resource, parent, false)
        view.findViewById<TextView>(R.id.db_item).text = data[position]
        if(childCount != null)
            view.findViewById<TextView>(R.id.db_item_child_count).text = childCount!![position]
        return view
    }

    override fun getItem(position: Int): String {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}