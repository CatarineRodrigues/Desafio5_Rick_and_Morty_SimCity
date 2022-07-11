package br.com.zup.rickandmorty.ui.details.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.UPDATE_STATUS_MSG_ERROR
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterListFavoriteState = MutableLiveData<ViewState<CharacterResult>>()

    fun updateCharactersFavorite(characterResult: CharacterResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.updateCharactersFavorite(characterResult)
                }
                characterListFavoriteState.value = response
            } catch (ex: Exception) {
                characterListFavoriteState.value =
                    ViewState.Error(Throwable(UPDATE_STATUS_MSG_ERROR))
            }
        }
    }
}