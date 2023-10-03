package com.spigi.rickandmortyandroidcompose.data.model

import com.google.gson.annotations.SerializedName

data class Character (
    val id: String,
    val name: String,
    @SerializedName("origin")
    val origin: Location,
    val status: String,
    val species: String,
    val image: String,
    var isFavorite: Boolean = false
)

data class Location(
    val name: String
)