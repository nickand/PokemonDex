package com.nickand.pokemondex

import retrofit2.http.GET
import com.nickand.pokemondex.PokemonResponse
import retrofit2.Call

interface PokeApiService {
    @get:GET("pokemon?limit=20")
    val pokemonList: Call<PokemonResponse?>?
}