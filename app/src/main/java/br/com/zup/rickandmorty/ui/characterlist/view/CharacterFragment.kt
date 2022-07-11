package br.com.zup.rickandmorty.ui.characterlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.rickandmorty.CHARACTER_KEY
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.FragmentCharacterBinding
import br.com.zup.rickandmorty.ui.characterlist.view.adapter.CharacterAdapter
import br.com.zup.rickandmorty.ui.characterlist.viewmodel.CharacterViewModel
import br.com.zup.rickandmorty.ui.home.view.HomeActivity
import br.com.zup.rickandmorty.ui.viewstate.ViewState

class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(arrayListOf(), this::goToCharacterDetail)
    }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarAccess()
        goToFavoriteList()
    }

    override fun onResume() {
        super.onResume()
        setUpRvMovieList()
        viewModel.getAllCharacters()
        intObserver()
    }

    private fun intObserver() {
        viewModel.characterResponse.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateCharacterList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

/*        viewModel.characterFavoritedState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "O personagem '${it.data.name}' foi favoritado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }*/
    }

    private fun setUpRvMovieList() {
        binding.rvCharacterlist.adapter = adapter
        binding.rvCharacterlist.layoutManager = GridLayoutManager(context, 2)
    }

    private fun goToCharacterDetail(character: CharacterResult) {
        val bundle = bundleOf(CHARACTER_KEY to character)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_characterFragment_to_detailsFragment, bundle
        )
    }

    private fun actionBarAccess() {
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as HomeActivity).supportActionBar?.setTitle(R.string.app_name)
    }

    private fun goToFavoriteList(){
        binding.floatingActionButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_characterFragment_to_favoriteListFragment
            )
        }
    }
}