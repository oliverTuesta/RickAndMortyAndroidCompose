package com.spigi.rickandmortyandroidcompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.spigi.rickandmortyandroidcompose.data.model.Character

@Composable
fun CharacterList(characters: List<Character>, navController: NavController) {
    Column {
        LazyColumn {
            items(characters) { character ->
                CharacterCard(character = character, navController = navController)
            }
        }
    }
}