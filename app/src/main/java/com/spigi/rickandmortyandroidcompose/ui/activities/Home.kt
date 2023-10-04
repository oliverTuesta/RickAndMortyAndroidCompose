package com.spigi.rickandmortyandroidcompose.ui.activities

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.spigi.rickandmortyandroidcompose.ui.Routes

@Composable
fun Home(navController: NavController) {
    Row {
        Button(onClick = {navController.navigate(Routes.CharacterBrowser.route)}) {
            Text(text = "Browse characters")
        }
        Button(onClick = {navController.navigate(Routes.Favorites.route)}) {
            Text(text = "Favorite characters")
        }
    }
}