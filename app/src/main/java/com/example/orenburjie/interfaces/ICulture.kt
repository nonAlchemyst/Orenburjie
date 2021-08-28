package com.example.orenburjie.interfaces

interface ICulture {
    interface DataReceiving {
        fun onSuccess()
        fun onCancelled()
    }
}