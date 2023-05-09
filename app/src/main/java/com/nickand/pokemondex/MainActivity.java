package com.nickand.pokemondex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PokeApiService pokeApiService;
    private RecyclerView recyclerView;
    private PokemonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pokeApiService = RetrofitClient.getClient("https://pokeapi.co/api/v2/").create(PokeApiService.class);
        fetchPokemonList();
    }

    private void fetchPokemonList() {
        Call<PokemonResponse> call = pokeApiService.getPokemonList();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    List<Pokemon> pokemonList = pokemonResponse.getPokemonList();
                    adapter = new PokemonListAdapter(MainActivity.this, pokemonList);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                // Handle network error
            }
        });
    }
}