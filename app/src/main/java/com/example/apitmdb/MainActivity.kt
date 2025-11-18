package com.example.apitmdb

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apitmdb.api.RetrofitHelper
import com.example.apitmdb.databinding.ActivityMainBinding
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
                recuperarFilmesPopulares()
            }
        }


    }

    private suspend fun recuperarFilmesPopulares() {


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