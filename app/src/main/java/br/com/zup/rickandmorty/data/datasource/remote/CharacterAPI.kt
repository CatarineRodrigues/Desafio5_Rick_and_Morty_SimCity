package br.com.zup.rickandmorty.data.datasource.remote

import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {

    @GET("/character")
    suspend fun getAllCharactersNetwork(
        @Query("image")
        image: String,
        @Query("name")
        name: String?,
        @Query("status")
        status: String?,
        @Query("species")
        species: String?,
        @Query("gender")
        gender: String?,
        ): CharacterResponse
}