package com.example.orenburjie.EditData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.orenburjie.EditEkskursiiActivity
import com.example.orenburjie.objects.Item
import com.example.orenburjie.R
import com.example.orenburjie.objects.RestingPlace
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class EditDataActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var storage: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var title: EditText
    lateinit var description: EditText
    lateinit var latitude: EditText
    lateinit var longitude: EditText
    lateinit var push: Button
    lateinit var addRP: Button
    lateinit var listener: ValueEventListener
    lateinit var list: ListView
    lateinit var backListBtn: Button
    lateinit var LinkList: ArrayList<String>
    lateinit var toEditCulture: Button
    lateinit var toEditExcursions: Button
    lateinit var add_link: Button
    lateinit var imageLink: EditText
    lateinit var linksList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)
        ref =  FirebaseDatabase.getInstance().getReference("Priroda")
        storage = Firebase.storage
        storageRef = FirebaseStorage.getInstance().getReference("Interesnie Mesta")

        title = findViewById(R.id.edit_title)
        description = findViewById(R.id.edit_description)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        push = findViewById(R.id.push)
        addRP = findViewById(R.id.addRP)
        list = findViewById(R.id.editData_ItemsList)
        backListBtn = findViewById(R.id.editData_ItemsList_back)
        toEditCulture = findViewById(R.id.editData_to_edit_culture)
        toEditExcursions = findViewById(R.id.editData_to_editExcursions)
        add_link = findViewById(R.id.ED_add_link)
        imageLink = findViewById(R.id.ED_link)
        linksList = findViewById(R.id.ED_links_list)
        toEditCulture.setOnClickListener {
            startActivity(Intent(this, EditCultureDataActivity::class.java))
        }

        toEditExcursions.setOnClickListener {
            startActivity(Intent(this, EditEkskursiiActivity::class.java))
        }

        addRP.setOnClickListener {
            startActivity(Intent(this, AddRestingPlacesActivity::class.java))
        }

        push.setOnClickListener {
            push()
        }
        LinkList = ArrayList()
        add_link.setOnClickListener {
            LinkList.add(imageLink.text.toString())
            linksList.adapter = ListAdapter(R.layout.edit_data_items_list_item, LinkList)
        }
        getItems()
    }

    private fun push(){
        if(LinkList.size == 0){
            Toast.makeText(this, "Не были указаны ссылки на фотографии", Toast.LENGTH_SHORT).show()
        }
        val itemId = ref.push().key

        val restingPlaces = ArrayList<RestingPlace>()
        restingPlaces.add(RestingPlace("Name", "Street", "phone", "link"))
        val item = Item(itemId!!, title.text.toString(), description.text.toString(), LinkList, latitude.text.toString(), longitude.text.toString(), restingPlaces)
        ref.child(itemId).setValue(item).addOnCompleteListener {
            Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show()
        }
    }

    private fun getItems(){
        var arr= ArrayList<String>()
        var childs = ArrayList<String>()
        listener = ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditDataActivity, "Ошибка загрузки мест отдыха", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children){
                    arr.add(item.key.toString() + if(item.childrenCount.toInt() == 0) " "+cutString(item.value.toString()) else "")
                    childs.add(item.childrenCount.toString())
                }
                list.adapter = ListAdapter(R.layout.edit_data_items_list_item, arr, childs)
                ref.removeEventListener(listener)
            }
        })
        list.setOnItemClickListener { parent, view, position, id ->
            if(childs[position].toInt() > 0) {
                ref = ref.child(arr[position])
                getItems()
            }
        }
        backListBtn.setOnClickListener {
            if(ref != ref.root) {
                ref = ref.parent!!
                getItems()
            }
        }
    }

    private fun cutString(s: String): String{
        val length = 15
        var i = 0
        var out: String = ""
        for(symb in s)
        {
            if(i == length){
                break
            }
            out += symb
            i++
        }
        if(s.length > length)
            out += "..."
        return out
    }
}