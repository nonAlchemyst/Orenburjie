package com.example.orenburjie.priroda.interfaces

import com.google.firebase.database.DatabaseReference

interface OnTransferReference {
    fun getReference(fragment:Int): DatabaseReference?
}