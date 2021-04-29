package com.example.orenburjie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.orenburjie.EditData.ListAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditEkskursiiActivity : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var description: EditText
    lateinit var linksList: ListView
    lateinit var LinkList: ArrayList<String>
    lateinit var ref: DatabaseReference
    lateinit var push: Button
    lateinit var add_link: Button
    lateinit var imageLink: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ekskursii)
        ref = FirebaseDatabase.getInstance().getReference("Excursions")
        title = findViewById(R.id.EDE_title)
        description = findViewById(R.id.EDE_description)
        add_link = findViewById(R.id.EDE_add_image_link)
        linksList = findViewById(R.id.EDE_links_list)
        push = findViewById(R.id.EDE_add_item)
        imageLink = findViewById(R.id.EDE_image_link)
        LinkList = ArrayList()
        add_link.setOnClickListener {
            LinkList.add(imageLink.text.toString())
            linksList.adapter = ListAdapter(R.layout.edit_data_items_list_item, LinkList)
        }
        push.setOnClickListener {
            push()
        }
    }

    fun push(){
        if(LinkList.size == 0){
            Toast.makeText(this, "Не были указаны ссылки на фотографии", Toast.LENGTH_SHORT).show()
            return
        }
        val itemId = ref.push().key

        val item = Item(itemId!!, title.text.toString(), description.text.toString(), LinkList)
        ref.child(itemId).setValue(item).addOnCompleteListener {
            Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show()
        }
    }
}