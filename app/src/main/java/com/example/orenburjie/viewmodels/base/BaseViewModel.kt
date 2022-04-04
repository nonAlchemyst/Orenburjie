package com.example.orenburjie.viewmodels.base

import androidx.lifecycle.ViewModel
import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.interfaces.ICulture
import com.example.orenburjie.interfaces.OnMenuListItemClickListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

open class BaseViewModel: ViewModel(){

    protected var ref: DatabaseReference? = null
    private var refValueListener: ValueEventListener
    private var items = ArrayList<Item>()
    private lateinit var dataReceivingListener: ICulture.DataReceiving
    private var created: Boolean = true

    init {
        refValueListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                setNewData(snapshot)
                dataReceivingListener.onSuccess()
            }

            override fun onCancelled(error: DatabaseError) {
                dataReceivingListener.onCancelled()
            }
        }
        if(created){
            created = false
            onCreated()
        }
    }

    open fun onCreated(){}

    private fun downloadData(){
        ref?.addValueEventListener(refValueListener)
    }

    override fun onCleared() {
        ref?.removeEventListener(refValueListener)
    }

    private fun setNewData(snapshot: DataSnapshot){
        if(snapshot.exists()){
            items.clear()
            for (item in snapshot.children){
                item.getValue(Item::class.java)?.let { items.add(it) }
            }
        }
    }

    protected open fun next(){
        Router.getInstance().showDescriptionFragment()
    }

    fun setDataReceivingListener(listener: ICulture.DataReceiving){
        dataReceivingListener = listener
        downloadData()
    }

    fun getItems() = items

    val onItemClickListener = object : OnMenuListItemClickListener {
        override fun onItemClick(item: Item) {
            Repository.getInstance().setItem(item)
            next()
        }
    }

}