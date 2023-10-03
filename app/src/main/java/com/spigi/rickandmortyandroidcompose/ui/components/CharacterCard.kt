package com.spigi.rickandmortyandroidcompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.spigi.rickandmortyandroidcompose.data.local.AppDatabase
import com.spigi.rickandmortyandroidcompose.data.model.Character
import com.spigi.rickandmortyandroidcompose.repository.CharacterRepository
import com.spigi.rickandmortyandroidcompose.ui.Routes

@Composable
fun CharacterCard(character: Character, navController: NavController) {

    val (favorite, setFavorite) = remember {
        mutableStateOf(character.isFavorite)
    }
    val context = LocalContext.current
    val characterDao = AppDatabase.getInstance(context).characterDao()
    val characterRepository = CharacterRepository(characterDao = characterDao)

    Row(
        modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.weight(5f).clickable {
            navController.navigate(
                Routes.CharacterDetail.setId(
                    character.id
                )
            )
        }) {
            GlideImage(
                imageModel = { character.image },
                imageOptions = ImageOptions(contentScale = ContentScale.Fit),
                modifier = Modifier
                    .size(140.dp)
                    .weight(2f)
            )
            Column(
                modifier = Modifier.weight(5f)
            ) {
                Text(
                    text = character.name,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(
                    text = "${character.status} - ${character.species}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Origin: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Text(
                    text = character.origin.name,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
        }
        IconButton(modifier = Modifier.weight(1f), onClick = {
            if (character.isFavorite) {
                characterRepository.delete(character)
            } else {
                characterRepository.save(character)
            }
            setFavorite(!favorite)
        }) {
            Icon(
                Icons.Default.Favorite,
                "Favorite Icon",
                tint =
                if (favorite) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}