package com.nickand.pokemondex;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
    @GET("pokemon?limit=20")
    Call<PokemonResponse> getPokemonList();
}
