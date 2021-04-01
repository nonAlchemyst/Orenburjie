package com.example.orenburjie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.orenburjie.priroda.objects.RestingPlace
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddRestingPlacesActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var id: EditText
    lateinit var btnAdd: Button
    lateinit var name: EditText
    lateinit var link: EditText
    lateinit var phone: EditText
    lateinit var street: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_resting_places)
        btnAdd = findViewById(R.id.RP_btn_add)
        id = findViewById(R.id.RP_id)
        name = findViewById(R.id.RP_name)
        link = findViewById(R.id.RP_pathToSite)
        phone = findViewById(R.id.RP_phone)
        street = findViewById(R.id.RP_street)
        ref =  FirebaseDatabase.getInstance().getReference("Priroda").child("Interesnie Mesta")
        btnAdd.setOnClickListener {
            push(id.text.toString())
        }

    }

    private fun push(id: String){
        val restingPlace = RestingPlace(name.text.toString(),
                link.text.toString(),
                phone.text.toString(),
                street.text.toString())
        val arr = ArrayList<RestingPlace>()
        arr.add(restingPlace)
        ref.child(id).child("restingPlaces").setValue(arr).addOnCompleteListener {
            Toast.makeText(this, "Место отдыха добавлено", Toast.LENGTH_SHORT).show()
        }

    }
}