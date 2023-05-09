package com.nickand.pokemondex

import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.os.Bundle
import com.nickand.pokemondex.R
import android.content.Intent
import com.nickand.pokemondex.PokemonDetailActivity
import com.squareup.picasso.Picasso

class PokemonDetailActivity : AppCompatActivity() {
    private var pokemonImageView: ImageView? = null
    private var pokemonNameTextView: TextView? = null
    private var pokemonDescriptionTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        val intent = intent
        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME)
        val pokemonImageUrl = intent.getStringExtra(EXTRA_POKEMON_IMAGE_URL)
        val pokemonDescription = intent.getStringExtra(EXTRA_POKEMON_DESCRIPTION)
        pokemonImageView = findViewById(R.id.pokemon_detail_image)
        pokemonNameTextView = findViewById(R.id.pokemon_detail_name)
        pokemonDescriptionTextView = findViewById(R.id.pokemon_detail_description)
        Picasso.get().load(pokemonImageUrl).into(pokemonImageView)
        setPokemonName(pokemonName)
        setPokemonDescription(pokemonDescription)
    }

    private fun setPokemonName(pokemonName: String?) {
        pokemonNameTextView?.let {
            it.text = pokemonName
        }
    }

    private fun setPokemonDescription(pokemonDescription: String?) {
        pokemonDescriptionTextView?.let {
            it.text = pokemonDescription
        }
    }

    companion object {
        const val EXTRA_POKEMON_NAME = "pokemon_name"
        const val EXTRA_POKEMON_IMAGE_URL = "pokemon_image_url"
        const val EXTRA_POKEMON_DESCRIPTION = "pokemon_description"
    }
}