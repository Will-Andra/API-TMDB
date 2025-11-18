package com.example.apitmdb.api

import com.example.apitmdb.model.Filme
import com.example.apitmdb.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmeAPI {

@GET("movie/popular?api_key=${RetrofitHelper.API_KEY}")
suspend fun recuperafFilmesPopulares(): Response<FilmeResposta>


}