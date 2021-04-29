package com.example.orenburjie.excursions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orenburjie.R
import com.example.orenburjie.priroda.interfaces.OnTransferReference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ExcursionsActivity : AppCompatActivity(), OnTransferReference {

    private var ref: DatabaseReference? = FirebaseDatabase.getInstance().getReference("Excursions")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excursions)
    }

    override fun getReference(fragment: Int): DatabaseReference? {
        when(fragment){
            R.id.excursionsFragment -> return ref
        }
        return null
    }

    override fun onBackPressed() {
        finish()
    }
}