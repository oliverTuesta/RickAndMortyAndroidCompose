package com.spigi.rickandmortyandroidcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spigi.rickandmortyandroidcompose.ui.activities.CharacterBrowser
import com.spigi.rickandmortyandroidcompose.ui.activities.CharacterDetail
import com.spigi.rickandmortyandroidcompose.ui.activities.Favorites
import com.spigi.rickandmortyandroidcompose.ui.activities.Home

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.CharacterBrowser.route) {
            CharacterBrowser(navController)
        }
        composable(Routes.Favorites.route) {
            Favorites(navController = navController)
        }
        composable(Routes.CharacterDetail.routeWithArgument,
            arguments = listOf(navArgument("id") { type = NavType.StringType})
        ) {backStackEntry ->
            CharacterDetail(backStackEntry.arguments?.getString(Routes.CharacterDetail.argument) as String, navController)
        }
        composable(Routes.Home.route) {
            Home(navController = navController)
        }
    }
}

sealed class Routes(val route: String) {
    object CharacterBrowser: Routes("CharacterBrowser")
    object Favorites: Routes("Favorites")
    object Home: Routes("Home")
    object CharacterDetail: Routes("CharacterDetail") {
        const val routeWithArgument = "CharacterDetail/{id}"
        const val argument = "id"
        fun setId(id: String): String {
            return "CharacterDetail/${id}"
        }
    }
}