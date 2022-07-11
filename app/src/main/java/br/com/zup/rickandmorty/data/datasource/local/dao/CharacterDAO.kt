package br.com.zup.rickandmorty.data.datasource.local.dao

import androidx.room.*
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character ORDER BY name ASC")
    fun getAllCharacters(): List<CharacterResult>

    @Query("SELECT * FROM character WHERE favorited = 1")
    fun getAllCharactersFavorited(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharactersDB(listCharacter : List<CharacterResult>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCharacterFavorite(character: CharacterResult)
}