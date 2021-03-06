package br.com.zup.rickandmorty.ui.favoritelist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.ERROR
import br.com.zup.rickandmorty.ERROR_
import br.com.zup.rickandmorty.GET_FAVORITED_LIST_MSG_ERROR
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListViewModel(application: Application) : AndroidViewModel(application)  {
    private val characterUseCase = CharacterUseCase(application)
    val characterListFavoriteState = MutableLiveData<ViewState<List<CharacterResult>>>()

    fun getAllCharactersFavorited() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharactersFavorited()
                }
                characterListFavoriteState.value = response
            } catch (ex: Exception) {
                characterListFavoriteState.value =
                    ViewState.Error(Throwable(GET_FAVORITED_LIST_MSG_ERROR))
                Log.i(ERROR, ERROR_ + ex.message)
            }
        }
    }
}