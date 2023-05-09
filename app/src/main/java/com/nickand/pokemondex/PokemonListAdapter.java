package com.nickand.pokemondex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {
    private List<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());

        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getPokemonIdFromUrl(pokemon.getUrl()) + ".png";
        Picasso.get().load(imageUrl).into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    private int getPokemonIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        return Integer.parseInt(urlParts[urlParts.length - 1]);
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pokemonImage;
        TextView pokemonName;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Pokemon selectedPokemon = pokemonList.get(position);

            String pokemonName = selectedPokemon.getName();
            String pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getPokemonIdFromUrl(selectedPokemon.getUrl()) + ".png";

            Intent intent = new Intent(context, PokemonDetailActivity.class);
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_NAME, pokemonName);
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_IMAGE_URL, pokemonImageUrl);

            // Add a brief description or any other information about the Pokemon
            String pokemonDescription = "This is a sample description for " + pokemonName;
            intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_DESCRIPTION, pokemonDescription);

            context.startActivity(intent);
        }
    }
}
