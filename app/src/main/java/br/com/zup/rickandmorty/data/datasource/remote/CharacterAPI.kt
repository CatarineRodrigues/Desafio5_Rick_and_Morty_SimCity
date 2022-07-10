package br.com.zup.rickandmorty.data.datasource.remote


import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {

    @GET("character")
    suspend fun getAllCharactersNetwork(
        @Query("page")
        pages: Int = 1
    ): CharacterResponse
}