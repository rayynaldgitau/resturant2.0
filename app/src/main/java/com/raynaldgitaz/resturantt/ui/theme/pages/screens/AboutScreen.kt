package com.raynaldgitaz.resturantt.ui.theme.pages.screens

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme

@Composable
fun AboutScreen(navController:NavHostController) {
    Text(text = "Welcome to about screen")
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AboutScreenPreview() {
    ResturanttTheme {
        AboutScreen(rememberNavController())
    }
}