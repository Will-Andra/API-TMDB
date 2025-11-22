package com.example.apitmdb.api

import android.text.Editable
import com.example.apitmdb.model.FilmeDetalhes
import com.example.apitmdb.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeAPI {

    @GET("movie/popular?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperafFilmesPopulares(): Response<FilmeResposta>

    @GET("movie/{movie_id}?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperarDetalhesFilme(
        @Path("movie_id") move_id: Int
    ): Response<FilmeDetalhes>

    @GET("movie/{movie_id}?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperarImagem(
        @Path("movie_id") move_id: Int
    ): Response<FilmeDetalhes>

    @GET("search/movie?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperarFilmePesquisa(
        @Query("query") pesquisa: Editable?
    ): Response<FilmeResposta>

}