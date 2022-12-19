package com.example.warningapp.ui

sealed class Screen(val route: String) {
    object ListScreen : Screen("list_screen")
    object WarningScreen : Screen("warning_screen")

    fun withArgs(vararg args: String): String {

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
