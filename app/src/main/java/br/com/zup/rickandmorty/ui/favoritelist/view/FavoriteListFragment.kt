package br.com.zup.rickandmorty.ui.favoritelist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.databinding.FragmentFavoriteListBinding
import br.com.zup.rickandmorty.ui.home.view.HomeActivity

class FavoriteListFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteListBinding

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
    }

    private fun actionBarAccess() {
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as HomeActivity).supportActionBar?.setTitle(R.string.favorites)
    }
}