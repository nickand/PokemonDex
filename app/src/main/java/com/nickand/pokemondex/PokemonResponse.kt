package com.nickand.pokemondex

import com.google.gson.annotations.SerializedName
import com.nickand.pokemondex.Pokemon

class PokemonResponse {
    @SerializedName("results")
    var pokemonList: List<Pokemon>? = null
}