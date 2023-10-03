package com.spigi.rickandmortyandroidcompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.spigi.rickandmortyandroidcompose.data.model.CharacterEntity

@Dao
interface CharacterDao {
    @Insert
    fun save(character: CharacterEntity)

    @Delete
    fun delete(character: CharacterEntity)

    @Query("select * from characters")
    fun getAll(): List<CharacterEntity>

    @Query("select * from characters where id = :id")
    fun getById(id: String): CharacterEntity?
}