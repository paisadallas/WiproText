package com.example.wiprotext.util

sealed class Screen(val route:String){
    object MainScreen : Screen("home")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args:String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
