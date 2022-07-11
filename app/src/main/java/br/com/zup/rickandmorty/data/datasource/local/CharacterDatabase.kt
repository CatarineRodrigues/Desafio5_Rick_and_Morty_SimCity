package br.com.zup.rickandmorty.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.rickandmorty.CHARACTER_DATABASE
import br.com.zup.rickandmorty.data.datasource.local.dao.CharacterDAO
import br.com.zup.rickandmorty.data.datasource.remote.model.CharacterResult

@Database(entities = [CharacterResult::class], version = 2)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    CHARACTER_DATABASE
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}