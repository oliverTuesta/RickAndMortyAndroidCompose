package com.spigi.rickandmortyandroidcompose.data.remote

object ApiClient {
    private const val API_BASE_URL = "https://rickandmortyapi.com/api/"
    private var characterService: CharacterService? = null

    fun getCharacterService(): CharacterService {
        if (characterService == null) {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
            characterService = retrofit.create(CharacterService::class.java)
        }
        return characterService as CharacterService
    }
}