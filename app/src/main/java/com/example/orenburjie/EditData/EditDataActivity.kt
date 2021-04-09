package com.example.orenburjie.EditData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.orenburjie.Item
import com.example.orenburjie.R
import com.example.orenburjie.priroda.objects.RestingPlace
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)
        //ref =  FirebaseDatabase.getInstance().getReference("Priroda").child("Interesnie Mesta")
        ref =  FirebaseDatabase.getInstance().getReference("Priroda").child("Zapovedniki")
        storage = Firebase.storage
        storageRef = FirebaseStorage.getInstance().getReference("Interesnie Mesta")

        title = findViewById(R.id.edit_title)
        description = findViewById(R.id.edit_description)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        push = findViewById(R.id.push)
        addRP = findViewById(R.id.addRP)
        addRP.setOnClickListener {
            startActivity(Intent(this, AddRestingPlacesActivity::class.java))
        }

        push.setOnClickListener {
            push()
        }

        /*storageRef.downloadUrl.addOnSuccessListener {
            val stroka = it.toString()
        }
        */
    }

    private fun push(){
        //ref =  FirebaseDatabase.getInstance().getReference("Priroda").child("Interesnie Mesta")
        //storage = Firebase.storage
        //storageRef = storage.reference

        val itemId = ref.push().key
        val list = ArrayList<String>()
        list.add("Priroda/Zapovedniki/Buzulukskii Bor/buzulukskii_bor1.jpg")
        list.add("Priroda/Zapovedniki/Buzulukskii Bor/buzulukskii_bor2.jpg")
        list.add("Priroda/Zapovedniki/Buzulukskii Bor/buzulukskii_bor3.jpg")

        val restingPlaces = ArrayList<RestingPlace>()
        restingPlaces.add(RestingPlace("Name", "Street", "phone", "link"))
        //list.add("Priroda/Interesnie Mesta/Ural/ural3.jpg")
        val item = Item(itemId!!, title.text.toString(), description.text.toString(), list, latitude.text.toString(), longitude.text.toString(), restingPlaces)
        ref.child(itemId).setValue(item).addOnCompleteListener {
            Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show()
        }
    }
}