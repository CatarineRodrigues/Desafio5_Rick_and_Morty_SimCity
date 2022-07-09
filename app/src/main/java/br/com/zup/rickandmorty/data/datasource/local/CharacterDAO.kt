package br.com.zup.rickandmorty.data.datasource.local

import androidx.room.*
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character ORDER BY id ASC")
    fun getAllCharacters(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharacterDB(listCharacter : List<CharacterResult>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCharacter(character: CharacterResult)
}