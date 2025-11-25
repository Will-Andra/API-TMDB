package com.example.apitmdb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{

        const val API_KEY = "8bc0ecbae8a9c01da41148475878ff07"
        const val TAMANHO_IMAGEM = "w1280"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGEM = "https://image.tmdb.org/t/p/"

        val filmeAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmeAPI::class.java)

        val imagemAPI = Retrofit.Builder()
            .baseUrl(BASE_URL_IMAGEM)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmeAPI::class.java)
    }

}