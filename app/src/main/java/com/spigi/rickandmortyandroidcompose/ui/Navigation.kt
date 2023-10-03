package com.spigi.rickandmortyandroidcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spigi.rickandmortyandroidcompose.ui.activities.CharacterBrowser
import com.spigi.rickandmortyandroidcompose.ui.activities.CharacterDetail

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.CharacterBrowser.route) {
        composable(Routes.CharacterBrowser.route) {
            CharacterBrowser(navController)
        }
        composable(Routes.CharacterDetail.routeWithArgument,
            arguments = listOf(navArgument("id") { type = NavType.StringType})
        ) {backStackEntry ->
            CharacterDetail(backStackEntry.arguments?.getString(Routes.CharacterDetail.argument) as String, navController)
        }
    }
}

sealed class Routes(val route: String) {
    object CharacterBrowser: Routes("CharacterBrowser")
    object CharacterDetail: Routes("CharacterDetail") {
        const val routeWithArgument = "CharacterDetail/{id}"
        const val argument = "id"
        fun setId(id: String): String {
            return "CharacterDetail/${id}"
        }
    }
}