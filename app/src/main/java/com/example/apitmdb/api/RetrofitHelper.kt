package com.example.apitmdb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{

        const val API_KEY = "8bc0ecbae8a9c01da41148475878ff07"

        val filmeAPI = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmeAPI::class.java)
    }

}