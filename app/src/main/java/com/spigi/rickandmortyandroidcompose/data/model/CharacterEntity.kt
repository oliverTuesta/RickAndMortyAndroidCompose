package com.spigi.rickandmortyandroidcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity (
    @PrimaryKey val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
) {
    fun toCharacter(): Character {
        return Character(
            id = id,
            name = name,
            status = status,
            species = species,
            image = image,
            origin = Location(name = "unknown"),
            isFavorite = true
        )
    }
}