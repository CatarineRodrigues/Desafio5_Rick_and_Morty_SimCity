package br.com.zup.rickandmorty.ui.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.rickandmorty.*
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.FragmentDetailsBinding
import br.com.zup.rickandmorty.ui.details.viewmodel.DetailsViewModel
import br.com.zup.rickandmorty.ui.home.view.HomeActivity
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarAccess()
        getCharacterDetail()
        favoriteCharacter()
        intObserver()
    }

    private fun getCharacter(): CharacterResult? = arguments?.getParcelable(CHARACTER_KEY)

    private fun getCharacterDetail() {
        val character = getCharacter()
        character?.let {
            Picasso.get().load(it.image).into(binding.imageCharacter)
            binding.tvName.text = NAME + it.name
            binding.tvStatus.text = STATUS + it.status
            binding.tvSpecies.text = SPECIES + it.species
            binding.tvGender.text = GENDER + it.gender

            (activity as HomeActivity).supportActionBar?.title = it.name
            setStarColor(character)
        }
    }

    private fun actionBarAccess() {
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun favoriteCharacter() {
        val character = getCharacter()
        character?.let {
            binding.icFavorite.setOnClickListener {
                setStarColor(character)
                character.favorited = !character.favorited
                updateCharactersFavorite(character)
                if (character.favorited) {
                    Toast.makeText(context,
                        "O personagem '${character.name}' foi favoritado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    updateCharactersFavorite(character)
                    Toast.makeText(context,
                        "O personagem '${character.name}' foi desfavoritado",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun updateCharactersFavorite(characterResult: CharacterResult) {
        viewModel.updateCharactersFavorite(characterResult)
    }

    private fun intObserver() {
        viewModel.characterListFavoriteState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    setStarColor(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    private fun setStarColor(character: CharacterResult) {
        binding.icFavorite.setImageDrawable(
            ContextCompat.getDrawable(binding.root.context,
                if (character.favorited)
                    R.drawable.ic_favorite_star
                else
                    R.drawable.ic_disfavorite_star
            )
        )
    }
}