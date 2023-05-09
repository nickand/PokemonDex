package com.nickand.pokemondex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PokemonDetailActivity extends AppCompatActivity {

    public static final String EXTRA_POKEMON_NAME = "pokemon_name";
    public static final String EXTRA_POKEMON_IMAGE_URL = "pokemon_image_url";
    public static final String EXTRA_POKEMON_DESCRIPTION = "pokemon_description";

    private ImageView pokemonImageView;
    private TextView pokemonNameTextView;
    private TextView pokemonDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Intent intent = getIntent();
        String pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME);
        String pokemonImageUrl = intent.getStringExtra(EXTRA_POKEMON_IMAGE_URL);
        String pokemonDescription = intent.getStringExtra(EXTRA_POKEMON_DESCRIPTION);

        pokemonImageView = findViewById(R.id.pokemon_detail_image);
        pokemonNameTextView = findViewById(R.id.pokemon_detail_name);
        pokemonDescriptionTextView = findViewById(R.id.pokemon_detail_description);

        Picasso.get().load(pokemonImageUrl).into(pokemonImageView);
        pokemonNameTextView.setText(pokemonName);
        pokemonDescriptionTextView.setText(pokemonDescription);
    }
}
