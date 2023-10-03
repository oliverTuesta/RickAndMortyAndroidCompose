package com.spigi.rickandmortyandroidcompose.ui.activities

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spigi.rickandmortyandroidcompose.data.local.AppDatabase
import com.spigi.rickandmortyandroidcompose.data.model.Character
import com.spigi.rickandmortyandroidcompose.repository.CharacterRepository
import com.spigi.rickandmortyandroidcompose.ui.components.CharacterList
import com.spigi.rickandmortyandroidcompose.util.Result

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CharacterBrowser(navController: NavController) {
    val (searchParam, setSearchParam) = remember {
        mutableStateOf("")
    }
    val (characters, setCharacters) = remember {
        mutableStateOf(listOf<Character>())
    }
    val context = LocalContext.current
    val characterDao = AppDatabase.getInstance(context).characterDao()
    val characterRepository = CharacterRepository(characterDao = characterDao)

    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        OutlinedTextField (
            value = searchParam,
            singleLine = true,
            onValueChange = { newTerm -> setSearchParam(newTerm) },
            placeholder = { Text(text = "Rick...") },
            leadingIcon = { Icon(Icons.Filled.Search, "Search icon") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    characterRepository.getCharactersByName(searchParam) {result ->
                        if (result is Result.Success) {
                            setCharacters(result.data!!)
                        } else {
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    keyboardController?.hide()
                },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        CharacterList(characters = characters, navController = navController)
    }
}