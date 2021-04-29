package com.example.orenburjie.excursions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.orenburjie.Item
import com.example.orenburjie.R
import com.example.orenburjie.priroda.interfaces.OnTransferItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExcursionsItemActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener, OnTransferItem {

    lateinit var item: Item
    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excursions_item)
        bottomNav = findViewById(R.id.excursions_item_bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(this)
        navController = Navigation.findNavController(this, R.id.ExcursionsItemFragment)
        item = intent.getSerializableExtra("Item") as Item
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == bottomNav?.selectedItemId)
            return false
        navController?.navigate(item.itemId)
        return true
    }

    override fun transferItem(): Item {
        return item
    }

    override fun onBackPressed() {
        finish()
    }
}