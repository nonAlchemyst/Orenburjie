package com.example.orenburjie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.orenburjie.EditData.EditDataActivity
import com.example.orenburjie.cultura.CultureActivity
import com.example.orenburjie.excursions.ExcursionsActivity
import com.example.orenburjie.priroda.PrirodaActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    lateinit var btnPriroda: Button
    lateinit var btnCultura: Button
    lateinit var btnChill: Button
    lateinit var image: ImageView
    lateinit var ref: DatabaseReference
    lateinit var storage: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var nature: ImageView
    lateinit var culture: ImageView
    lateinit var excursions: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!Global.isInitialize) {
            Firebase.database.setPersistenceEnabled(true)
            MapKitFactory.setApiKey(Global.map_apikey)
            MapKitFactory.initialize(this)
            Global.isInitialize = true
        }
        nature = findViewById(R.id.nature)
        culture = findViewById(R.id.culture)
        excursions = findViewById(R.id.excursions)
        var developer_btn = findViewById<Button>(R.id.developer_button)
        developer_btn.setOnClickListener {
            startActivity(Intent(this, EditDataActivity::class.java))
        }
        nature.setOnClickListener {
            startActivity(Intent(this, PrirodaActivity::class.java))
        }

        culture.setOnClickListener {
            startActivity(Intent(this, CultureActivity::class.java))
        }

        excursions.setOnClickListener {
            startActivity(Intent(this, ExcursionsActivity::class.java))
        }

    }

}