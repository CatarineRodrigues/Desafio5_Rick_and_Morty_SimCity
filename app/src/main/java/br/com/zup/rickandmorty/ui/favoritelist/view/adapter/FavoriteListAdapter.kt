package br.com.zup.rickandmorty.ui.favoritelist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class FavoriteListAdapter(
    private var characterFavoritedList: MutableList<CharacterResult>,
    private val clickCharacterFavorited: (characterResult: CharacterResult) -> Unit,
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterFavoritedList[position]
        holder.showMovieInfo(character)
        holder.binding.cvItem.setOnClickListener {
            clickCharacterFavorited(character)
        }
    }

    override fun getItemCount() = characterFavoritedList.size

    fun updateFavoriteList(newList: MutableList<CharacterResult>) {
        characterFavoritedList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showMovieInfo(characterResult: CharacterResult) {
            binding.tvCharacter.text = characterResult.name
            Picasso.get().load(characterResult.image)
                .into(binding.imageCharacter)
        }
    }
}