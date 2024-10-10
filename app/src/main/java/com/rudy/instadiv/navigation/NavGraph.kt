package com.rudy.instadiv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rudy.instadiv.screens.DetailScreen
import com.rudy.instadiv.screens.HomeScreen

@Composable
fun NavGraph()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(onSubmit = {selectedTag ->
            navController.navigate("detail/$selectedTag")

        }) }
        composable("detail/{tag}") {backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag")
            if (tag!=null){
                DetailScreen(tag){
                    navController.popBackStack()
                }
            }
        }
    }

}