package com.example.orenburjie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.orenburjie.priroda.PrirodaItemActivity

class AdapterList(private val context: Context, private val resource: Int, private val items: ArrayList<Item>): BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)

        val image = view.findViewById<ImageView>(R.id.photo)
        val path = items[position].images?.get(0)!!
        Global.loadImageFromStorage(path, image)
        view.findViewById<TextView>(R.id.name).text = items[position].title

        return view
    }

    override fun getItem(position: Int): Item {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}