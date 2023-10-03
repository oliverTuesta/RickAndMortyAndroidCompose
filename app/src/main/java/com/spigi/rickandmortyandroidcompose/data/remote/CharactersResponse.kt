package com.spigi.rickandmortyandroidcompose.data.remote

import com.google.gson.annotations.SerializedName
import com.spigi.rickandmortyandroidcompose.data.model.Character

data class CharactersResponse (
    @SerializedName("results")
    val characters: List<Character>
)