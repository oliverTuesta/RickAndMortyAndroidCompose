package com.spigi.rickandmortyandroidcompose.data.remote

import com.spigi.rickandmortyandroidcompose.data.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterService {

    @GET("character")
    fun getCharactersByPage(@Query("page") page: Int) : Call<CharactersResponse>

    @GET("character")
    fun getCharactersByName(@Query("name") name: String) : Call<CharactersResponse>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: String) : Call<Character>
}