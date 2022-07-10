package br.com.zup.rickandmorty.ui.characterlist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import br.com.zup.rickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    private val _characterResponse = MutableLiveData<ViewState<List<CharacterResult>>>()
    val characterFavoritedState = MutableLiveData<ViewState<CharacterResult>>()
    val characterResponse: LiveData<ViewState<List<CharacterResult>>> = _characterResponse

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharactersNetwork()
                }
                _characterResponse.value = response
            } catch (ex: Exception) {
                _characterResponse.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista vinda da internet!"))
                Log.i("Error", "Error ----- > ${ex.message}")
            }
        }
    }

    fun updateCharacterFavorite(character: CharacterResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.updateCharacterFavorite(character)
                }
                characterFavoritedState.value = response
            } catch (ex: Exception) {
                characterFavoritedState.value =
                    ViewState.Error(Throwable("Não foi possível atualizar o personagem"))
                Log.i("Error", "Error ----- > ${ex.message}")
            }
        }
    }
}