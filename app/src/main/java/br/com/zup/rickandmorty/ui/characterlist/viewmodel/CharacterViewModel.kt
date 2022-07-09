package br.com.zup.rickandmorty.ui.characterlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResponse
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel: ViewModel() {
    private val _characterResponse = MutableLiveData<List<CharacterResult>>()
    val characterResponse: LiveData<List<CharacterResult>> = _characterResponse

    fun getAllCharactersNetwork() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitService.apiService.getAllCharactersNetwork()
                }
                _characterResponse.value = response.characterResults
            } catch (ex: Exception) {
                Log.i("Error", "Error ----- > ${ex.message}")
            }
        }
    }
}