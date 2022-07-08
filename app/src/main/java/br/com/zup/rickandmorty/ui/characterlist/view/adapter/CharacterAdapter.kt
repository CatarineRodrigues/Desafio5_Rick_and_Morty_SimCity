package br.com.zup.rickandmorty.ui.characterlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding

class CharacterAdapter(
    private var listCharacter: MutableList<CharacterResult>,
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root)
}