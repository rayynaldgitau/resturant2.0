package com.raynaldgitaz.resturantt.bottomnav

import com.raynaldgitaz.resturantt.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int,
) {
    //for Food section
    object Food: BottomBarScreen(
        route = "foods",
        title = "foods",
        icon = R.drawable.food,
        icon_focused = R.drawable.food_focused
    )
    // for Drinks Section
    object Drinks: BottomBarScreen(
        route = "drinks",
        title = "drinks",
        icon = R.drawable.drink,
        icon_focused = R.drawable.drink_focused
    )
    // for About Section
    object About : BottomBarScreen(
        route = "about",
        title = "about",
        icon = R.drawable.about,
        icon_focused = R.drawable.about_focused
    )
}
