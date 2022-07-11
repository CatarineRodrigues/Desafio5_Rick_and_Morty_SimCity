package br.com.zup.rickandmorty.domain.usecase

import android.app.Application
import br.com.zup.rickandmorty.GET_DB_MSG_ERROR
import br.com.zup.rickandmorty.GET_FAVORITED_LIST_MSG_ERROR
import br.com.zup.rickandmorty.UPDATE_STATUS_MSG_ERROR
import br.com.zup.rickandmorty.data.datasource.local.CharacterDatabase
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult
import br.com.zup.rickandmorty.domain.repository.CharacterRepository
import br.com.zup.rickandmorty.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val characterDao = CharacterDatabase.getDatabase(application).characterDao()
    private val characterRepository = CharacterRepository(characterDao)

    private suspend fun getAllCharacters(): ViewState<List<CharacterResult>> {
        return try {
            val character = characterRepository.getAllCharacters()
            ViewState.Success(character)
        } catch (ex: Exception) {
            ViewState.Error(Exception(GET_DB_MSG_ERROR))
        }
    }

    suspend fun getAllCharactersNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val response = characterRepository.getAllCharactersNetwork()
            characterRepository.insertAllCharactersDB(response.characterResults)
            ViewState.Success(response.characterResults)
            getAllCharacters()
        }catch (ex: Exception){
            getAllCharacters()
        }
    }

    suspend fun updateCharactersFavorite(character: CharacterResult): ViewState<CharacterResult>{
        return try {
            characterRepository.updateCharacterFavorite(character)
            ViewState.Success(character)
        } catch (ex: Exception){
            ViewState.Error(Exception(UPDATE_STATUS_MSG_ERROR))
        }
    }

    suspend fun getAllCharactersFavorited(): ViewState<List<CharacterResult>> {
        return try {
            val character = characterRepository.getAllCharactersFavorited()
            ViewState.Success(character)
        } catch (ex: Exception){
            ViewState.Error(Exception(GET_FAVORITED_LIST_MSG_ERROR))
        }
    }
}