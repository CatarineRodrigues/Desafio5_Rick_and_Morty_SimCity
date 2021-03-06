package br.com.zup.rickandmorty.domain.repository

import br.com.zup.rickandmorty.data.datasource.local.dao.CharacterDAO
import br.com.zup.rickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResponse
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult

class CharacterRepository(private val characterDAO: CharacterDAO) {

    suspend fun getAllCharacters(): List<CharacterResult> = characterDAO.getAllCharacters()

    suspend fun getAllCharactersNetwork(): CharacterResponse =
        RetrofitService.apiService.getAllCharactersNetwork()

    suspend fun insertAllCharactersDB(characterList: List<CharacterResult>) =
        characterDAO.insertAllCharactersDB(characterList)

    suspend fun updateCharacterFavorite(character: CharacterResult){
        characterDAO.updateCharacterFavorite(character)
    }

    suspend fun getAllCharactersFavorited(): List<CharacterResult> = characterDAO.getAllCharactersFavorited()
}