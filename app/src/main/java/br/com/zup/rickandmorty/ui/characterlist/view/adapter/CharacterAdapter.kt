package br.com.zup.rickandmorty.ui.characterlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.URL_BASE_IMAGE
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private var characterList: MutableList<CharacterResult>,
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = characterList.size

    fun updateCharacterList(newList: MutableList<CharacterResult>) {
        characterList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showCharacterInfo(characterResult: CharacterResult) {
            binding.tvCharacter.text = characterResult.name
            Picasso.get().load(characterResult.image)
                .into(binding.imageCharacter)
        }
    }

}