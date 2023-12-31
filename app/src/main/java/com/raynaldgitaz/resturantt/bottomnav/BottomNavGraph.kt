package com.raynaldgitaz.resturantt.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raynaldgitaz.resturantt.data.BottomBarScreen
import com.raynaldgitaz.resturantt.ui.theme.pages.beta.ViewUploadDrinksScreen
import com.raynaldgitaz.resturantt.ui.theme.pages.screens.AboutScreen
import com.raynaldgitaz.resturantt.ui.theme.pages.foodview.ViewUploadsScreen

@Composable
fun BottomNavGraph(navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Food.route )
    {
        composable(route = BottomBarScreen.Food.route)
        {
            ViewUploadsScreen(navController)
        }
        composable(route = BottomBarScreen.Drinks.route)
        {
            ViewUploadDrinksScreen(navController)
        }
        composable(route = BottomBarScreen.About.route)
        {
            AboutScreen(navController)
        }
    }
}