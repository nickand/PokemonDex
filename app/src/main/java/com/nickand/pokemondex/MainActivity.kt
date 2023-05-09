package com.nickand.pokemondex

import androidx.appcompat.app.AppCompatActivity
import com.nickand.pokemondex.PokeApiService
import androidx.recyclerview.widget.RecyclerView
import com.nickand.pokemondex.PokemonListAdapter
import android.os.Bundle
import com.nickand.pokemondex.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickand.pokemondex.RetrofitClient
import com.nickand.pokemondex.PokemonResponse
import com.nickand.pokemondex.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var pokeApiService: PokeApiService
    private var recyclerView: RecyclerView? = null
    private var adapter: PokemonListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this)
        }
        pokeApiService = RetrofitClient.getClient("https://pokeapi.co/api/v2/")?.create(
            PokeApiService::class.java
        )!!
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        val call = pokeApiService.pokemonList
        call?.enqueue(object : Callback<PokemonResponse?> {
            override fun onResponse(
                call: Call<PokemonResponse?>,
                response: Response<PokemonResponse?>
            ) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    val pokemonList = pokemonResponse?.pokemonList
                    adapter = PokemonListAdapter(this@MainActivity, pokemonList ?: emptyList())
                    recyclerView?.adapter = adapter
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<PokemonResponse?>, t: Throwable) {
                // Handle network error
            }
        })
    }
}