package br.com.zup.rickandmorty.ui.favoritelist.view

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
import br.com.zup.rickandmorty.databinding.FragmentFavoriteListBinding
import br.com.zup.rickandmorty.ui.favoritelist.view.adapter.FavoriteListAdapter
import br.com.zup.rickandmorty.ui.favoritelist.viewmodel.FavoriteListViewModel
import br.com.zup.rickandmorty.ui.home.view.HomeActivity
import br.com.zup.rickandmorty.ui.viewstate.ViewState

class FavoriteListFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteListBinding

    private val adapter: FavoriteListAdapter by lazy {
        FavoriteListAdapter(arrayListOf(), this::goToCharacterDetail)
    }

    private val viewModel: FavoriteListViewModel by lazy {
        ViewModelProvider(this)[FavoriteListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarAccess()
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCharactersFavorited()
        initObserver()
    }

    private fun actionBarAccess() {
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as HomeActivity).supportActionBar?.setTitle(R.string.favorites)
    }

    private fun initObserver() {
        viewModel.characterListFavoriteState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateFavoriteList(it.data.toMutableList())
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

        viewModel.characterListFavoriteState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "Filme ${it.data[position].name} foi desfavoritado!",
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
    }

    private fun setRecyclerView() {
        binding.rvCharacterlist.adapter = adapter
        binding.rvCharacterlist.layoutManager = GridLayoutManager(context, 2)
    }

    private fun goToCharacterDetail(character: CharacterResult) {
        val bundle = bundleOf(CHARACTER_KEY to character)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_favoriteListFragment_to_detailsFragment, bundle
        )
    }

}