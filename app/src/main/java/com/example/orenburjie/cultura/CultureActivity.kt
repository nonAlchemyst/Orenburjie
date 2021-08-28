package com.example.orenburjie.cultura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orenburjie.MainActivity
import com.example.orenburjie.R
import com.example.orenburjie.priroda.interfaces.OnTransferItem
import com.example.orenburjie.priroda.interfaces.OnTransferReference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CultureActivity : AppCompatActivity(), OnTransferReference {

    private var ref = MainActivity.instance?.firebase?.getReference("Culture")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_culture)
    }

    override fun getReference(fragment: Int): DatabaseReference? {
        when(fragment){
            R.id.cultureFragment -> return ref
        }
        return null
    }

    override fun onBackPressed() {
        finish()
    }
}