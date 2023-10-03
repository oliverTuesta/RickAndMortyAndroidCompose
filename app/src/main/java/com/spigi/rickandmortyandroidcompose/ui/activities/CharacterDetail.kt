package com.spigi.rickandmortyandroidcompose.ui.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

@Composable
fun CharacterDetail(id: String, navController: NavController) {
    val context = LocalContext.current
    val characterDao = AppDatabase.getInstance(context).characterDao()
    val characterRepository = CharacterRepository(characterDao = characterDao)
    val (character, setCharacter) = remember {
        mutableStateOf<Character?>(null)
    }

    characterRepository.getCharacterById(id) { result ->
        setCharacter(result.data)
    }

    if (character == null) {
        return
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GlideImage(
            imageModel = { character.image },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            modifier = Modifier.size(140.dp)
        )
        Text(
            text = character.name,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
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