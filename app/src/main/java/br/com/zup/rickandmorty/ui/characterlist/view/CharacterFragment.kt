package br.com.zup.rickandmorty.ui.characterlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.databinding.FragmentHomeBinding
import br.com.zup.rickandmorty.ui.characterlist.view.adapter.CharacterAdapter
import br.com.zup.rickandmorty.ui.characterlist.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(arrayListOf()
//            , this::goToMovieDetail, this::favoritedMovie
        )
    }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRvMovieList()
        viewModel.getAllCharactersNetwork()
        intObserver()
    }

    private fun intObserver() {
        viewModel.characterResponse.observe(this.viewLifecycleOwner) {
            adapter.updateCharacterList(it.characterResults as MutableList<CharacterResult>)
        }
    }

 /*   private fun initObserver() {
        viewModel.movieListState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateMovieList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.movieFavoritedState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "Filme ${it.data.title} foi favoritado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoading.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }
    */

    private fun setUpRvMovieList() {
        binding.rvCharacterlist.adapter = adapter
        binding.rvCharacterlist.layoutManager = GridLayoutManager(context, 2)
    }
}