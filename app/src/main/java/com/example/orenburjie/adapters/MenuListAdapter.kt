package com.example.orenburjie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orenburjie.objects.Item
import com.example.orenburjie.R
import com.example.orenburjie.Repository
import com.example.orenburjie.interfaces.OnMenuListItemClickListener

class MenuListAdapter(private val context: Context, private val items: List<Item>, private val layoutSrc: Int): RecyclerView.Adapter<MenuListAdapter.ViewItem>() {

    private var onItemClickListener: OnMenuListItemClickListener? = null

    inner class ViewItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var image: ImageView
        private lateinit var title: TextView

        fun bind(item: Item) = with(itemView){
            image = findViewById(R.id.photo)
            title = findViewById(R.id.name)
            item.images?.get(0)?.let { path ->
                //Global.loadImageFromStorage(path, image, context)
                Repository.loadImage(path, image, context)
            }
            title.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItem {
        val view = LayoutInflater.from(context).inflate(layoutSrc, parent, false)
        val holder = ViewItem(view)
        view.setOnClickListener {
            val holderPosition = holder.adapterPosition
            onItemClickListener?.onItemClick(items[holderPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewItem, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setOnMenuListItemClickListener(listener: OnMenuListItemClickListener){
        onItemClickListener = listener
    }

}