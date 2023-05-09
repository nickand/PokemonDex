package com.nickand.pokemondex;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokemonResponse {
    @SerializedName("results")
    private List<Pokemon> pokemonList;

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
