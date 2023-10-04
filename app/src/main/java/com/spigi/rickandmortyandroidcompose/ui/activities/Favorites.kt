package com.spigi.rickandmortyandroidcompose.ui.activities

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.spigi.rickandmortyandroidcompose.data.local.AppDatabase
import com.spigi.rickandmortyandroidcompose.ui.components.CharacterList

@Composable
fun Favorites(navController: NavController) {
    val context = LocalContext.current
    val characterDao = AppDatabase.getInstance(context).characterDao()

    val charactersEntities = characterDao.getAll()
    val characters = charactersEntities.map { it.toCharacter() }
    CharacterList(characters = characters, navController = navController)
}