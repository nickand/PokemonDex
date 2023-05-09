package com.nickand.pokemondex

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nickand.pokemondex.PokemonListAdapter.PokemonViewHolder
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent

class PokemonListAdapter(private val context: Context, private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.pokemonName.text = pokemon.name
        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getPokemonIdFromUrl(
                pokemon.url
            ) + ".png"
        Picasso.get().load(imageUrl).into(holder.pokemonImage)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    private fun getPokemonIdFromUrl(url: String?): Int {
        val urlParts = url!!.split("/").toTypedArray()
        return urlParts[urlParts.size - 1].toInt()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var pokemonImage: ImageView
        var pokemonName: TextView

        init {
            pokemonImage = itemView.findViewById(R.id.pokemon_image)
            pokemonName = itemView.findViewById(R.id.pokemon_name)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            val selectedPokemon = pokemonList[position]
            val pokemonName = selectedPokemon.name
            val pokemonImageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getPokemonIdFromUrl(
                    selectedPokemon.url
                ) + ".png"
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_NAME, pokemonName)
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_IMAGE_URL, pokemonImageUrl)

            // Add a brief description or any other information about the Pokemon
            val pokemonDescription = "This is a sample description for $pokemonName"
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_DESCRIPTION, pokemonDescription)
            context.startActivity(intent)
        }
    }
}