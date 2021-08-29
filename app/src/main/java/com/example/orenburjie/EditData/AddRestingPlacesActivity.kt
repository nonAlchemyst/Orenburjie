package com.example.orenburjie.EditData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.orenburjie.R
import com.example.orenburjie.objects.RestingPlace
import com.google.firebase.database.*

class AddRestingPlacesActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var id: EditText
    lateinit var btnAdd: Button
    lateinit var name: EditText
    lateinit var link: EditText
    lateinit var phone: EditText
    lateinit var street: EditText
    lateinit var listener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_resting_places)
        btnAdd = findViewById(R.id.RP_btn_add)
        id = findViewById(R.id.RP_id)
        name = findViewById(R.id.RP_name)
        link = findViewById(R.id.RP_pathToSite)
        phone = findViewById(R.id.RP_phone)
        street = findViewById(R.id.RP_street)
                //ref =  FirebaseDatabase.getInstance().getReference("Priroda").child("Interesnie Mesta")
        ref =  FirebaseDatabase.getInstance().getReference("Excursions")
        btnAdd.setOnClickListener {
            push(id.text.toString())
        }

    }

    private fun push(id: String){
        var arr= ArrayList<RestingPlace>()
        listener = ref.child(id).child("restingPlaces").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AddRestingPlacesActivity, "Ошибка загрузки мест отдыха", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (place in snapshot.children){
                    arr.add(place.getValue(RestingPlace::class.java)!!)
                }
                val restingPlace = RestingPlace(name.text.toString(),
                        link.text.toString(),
                        phone.text.toString(),
                        street.text.toString())

                arr.add(restingPlace)
                ref.child(id).child("restingPlaces").setValue(arr).addOnCompleteListener {
                    Toast.makeText(this@AddRestingPlacesActivity, "Место отдыха добавлено", Toast.LENGTH_SHORT).show()
                }
                ref.child(id).child("restingPlaces").removeEventListener(listener)
            }
        })

    }
}