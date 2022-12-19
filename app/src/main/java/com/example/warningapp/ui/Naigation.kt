package com.example.warningapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.warningapp.ui.list.ListScreen

import com.example.warningapp.ui.warning.WarningViewModel
import com.example.warningapp.ui.warning.WarningScreen
import com.example.warningapp.utilities.Constants.WARNING_CHANNEL

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(route = Screen.ListScreen.route) {
            ListScreen(navController = navController)
        }
        composable(
            route = Screen.WarningScreen.route + "/{$WARNING_CHANNEL}",
            arguments = listOf(
                navArgument(WARNING_CHANNEL) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<WarningViewModel>()
            entry.arguments?.getString(WARNING_CHANNEL)
                ?.let { WarningScreen(warningChannel = it, viewModel = viewModel) }
        }
    }
}
