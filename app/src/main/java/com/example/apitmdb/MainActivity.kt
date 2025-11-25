package com.example.apitmdb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.apitmdb.api.RetrofitHelper
import com.example.apitmdb.databinding.ActivityMainBinding
import com.example.apitmdb.model.FilmeDetalhes
import com.example.apitmdb.model.FilmeResposta
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val filmeAPI by lazy {
        RetrofitHelper.filmeAPI
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnConsultar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //API TMDB
                //recuperarFilmesPopulares()
                //recuperarDetalhesFilme()
                recuperarFilmePesquisa()
            }
        }

        binding.btnLimpartxt.setOnClickListener {
            binding.edtnumpostagem.setText("")
        }


    }

    private suspend fun recuperarFilmePesquisa() {

        //1116465 - A Legend
        //1242898 - Predator: Badlands
        //1197137 - Black Phone 2

        var retorno: Response<FilmeResposta>? = null
        var searchUser = binding.edtnumpostagem.text

        try {
            retorno = filmeAPI.recuperarFilmePesquisa(searchUser)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_TMDB", "Erro ao Recuperar Filmes pesquisa ${retorno?.code()}")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val filmePesquisa = retorno.body()
                val listaFilmes = filmePesquisa?.results

                Log.i("info_TMDB", "SUCESS CODIGO: ${retorno.code()}")

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val title = filme.title
                    Log.i("info_TMDB", "$id - $title")
                }
            }else{
                Log.i("info_TMDB", "Erro CODIGO: ${retorno.code()}")
            }
        }
    }
    private suspend fun recuperarDetalhesFilme() {

        //1116465 - A Legend
        //1242898 - Predator: Badlands
        //1197137 - Black Phone 2

        var retorno: Response<FilmeDetalhes>? = null

        try {
            retorno = filmeAPI.recuperarDetalhesFilme(1242898)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_TMDB", "Erro ao Recuperar Filmes detalhes ${retorno?.code()}")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val filmeDetalhes = retorno.body()
                val titulo = filmeDetalhes?.title
                val listagenero = filmeDetalhes?.genres
                val pais = filmeDetalhes?.production_countries?.get(0)

                //https://image.tmdb.org/t/p/  +  w500/  +  1E5baAaEse26fej7uHcjOgEE2t2.jpg
                val url_IMG = RetrofitHelper.BASE_URL_IMAGEM
                val size_IMG = RetrofitHelper.TAMANHO_IMAGEM
                val nomeImagem = filmeDetalhes?.backdrop_path

                withContext(Dispatchers.Main){
                    binding.txtRETORNO.text = "$titulo \n Genero: ${listagenero?.get(0)?.name}"
                    Picasso.get()
                        .load(url_IMG+size_IMG+nomeImagem)
                        .into(binding.imgPhoto)

                }

                Log.i("info_TMDB", "SUCESS CODIGO: ${retorno.code()}")
                Log.i("info_TMDB", "titulo: $titulo")
                Log.i("info_TMDB", "Pais: ${pais?.name}")

                listagenero?.forEach { genero ->
                    Log.i("info_TMDB", "Genero: ${genero.name}")
                }

             }else{
                Log.i("info_TMDB", "Erro CODIGO: ${retorno.code()}")
            }
        }
    }
    private suspend fun recuperarFilmesPopulares() {

        //1116465 - A Legend
        //1242898 - Predator: Badlands
        //1197137 - Black Phone 2

        var retorno: Response<FilmeResposta>? = null

        try {
            retorno = filmeAPI.recuperafFilmesPopulares()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_TMDB", "Erro ao Recuperar Filmes populares ${retorno?.code()}")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val filmeResposta = retorno.body()
                val listaFilmes = filmeResposta?.results

                val pagina = filmeResposta?.page
                val totalPaginas = filmeResposta?.total_pages
                val totalFilmes = filmeResposta?.total_results



                Log.i("info_TMDB", "SUCESS CODIGO: ${retorno.code()}")
                Log.i("info_TMDB", "Pagina: $pagina")
                Log.i("info_TMDB", "Total Paginas: $totalPaginas")
                Log.i("info_TMDB", "Total Filmes: $totalFilmes")

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val title = filme.title
                    Log.i("info_TMDB", "$id - $title")
                }
            }else{
                Log.i("info_TMDB", "Erro CODIGO: ${retorno.code()}")
            }
        }
    }




}