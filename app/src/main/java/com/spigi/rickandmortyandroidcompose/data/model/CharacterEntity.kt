package com.spigi.rickandmortyandroidcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity (
    @PrimaryKey val id: String
)
