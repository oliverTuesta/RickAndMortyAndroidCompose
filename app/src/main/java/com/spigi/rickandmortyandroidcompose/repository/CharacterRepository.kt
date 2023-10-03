package com.spigi.rickandmortyandroidcompose.repository

import com.spigi.rickandmortyandroidcompose.data.local.CharacterDao
import com.spigi.rickandmortyandroidcompose.data.model.Character
import com.spigi.rickandmortyandroidcompose.data.model.CharacterEntity
import com.spigi.rickandmortyandroidcompose.data.remote.ApiClient
import com.spigi.rickandmortyandroidcompose.data.remote.CharacterService
import com.spigi.rickandmortyandroidcompose.data.remote.CharactersResponse
import com.spigi.rickandmortyandroidcompose.util.Result
import retrofit2.Call
import retrofit2.Response

class CharacterRepository(
    private val characterService: CharacterService = ApiClient.getCharacterService(),
    private val characterDao: CharacterDao
) {
    fun getCharactersByName(name: String, callback: (Result<List<Character>>)->Unit) {
        val getByName = characterService.getCharactersByName(name)
        getByName.enqueue(object : retrofit2.Callback<CharactersResponse>{
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        response.body()!!.characters.forEach {character: Character ->
                            character.isFavorite = characterDao.getById(character.id) != null
                        }
                        callback(Result.Success(response.body()!!.characters))
                    } catch (e: Exception) {
                        callback(Result.Error(e.localizedMessage as String))
                    }
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                callback(Result.Error(t.localizedMessage as String ?: "Error while connecting to the network"))
            }

        })
    }
    fun getCharacterById(id: String, callback: (Result<Character>)->Unit) {
        val getByPage = characterService.getCharacterById(id)
        getByPage.enqueue(object : retrofit2.Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                try {
                    callback(Result.Success(response.body()!!))
                } catch (e: Exception) {
                    callback(Result.Error("Error while connecting to the network"))
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                callback(Result.Error(t.localizedMessage as String ?: "Error while connecting to the network"))
            }
        })
    }
    fun getCharactersByPage(page: Int = 1, callback: (Result<List<Character>>)->Unit) {
        val getByPage = characterService.getCharactersByPage(page)
        getByPage.enqueue(object : retrofit2.Callback<CharactersResponse>{
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        callback(Result.Success(response.body()!!.characters))
                    } catch (e: Exception) {
                        callback(Result.Error(e.localizedMessage as String))
                    }
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                callback(Result.Error(t.localizedMessage as String ?: "Error while connecting to the network"))
            }

        })
    }

    fun save(character: Character) {
        characterDao.save(CharacterEntity(character.id))
    }

    fun delete(character: Character) {
        characterDao.delete(CharacterEntity(character.id))
    }

    fun existsById(character: Character): Boolean {
        return characterDao.getById(character.id) != null
    }
}