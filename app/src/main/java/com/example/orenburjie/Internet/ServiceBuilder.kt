package com.example.orenburjie.Internet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val retrofit  = Retrofit.Builder()
        .baseUrl("http://cinema.areas.su/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> BuildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}