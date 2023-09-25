package com.raynaldgitaz.resturantt.ui.theme.pages.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raynaldgitaz.resturantt.R
import com.raynaldgitaz.resturantt.navigation.ROUTE_INTERIOR_HOMESCREEN

import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController:NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.backgroundxx), contentDescription ="Background", contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
   Column(
       modifier = Modifier
           .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       TopAppBar(
       title = {
           Text(text = "About Us",
               modifier = Modifier.padding(70.dp,10.dp,10.dp),
               fontSize = 40.sp,
               color = Color.White)
       },
       navigationIcon = {
           IconButton(onClick = {navController.navigate(ROUTE_INTERIOR_HOMESCREEN)}) {
               Icon(Icons.Filled.ArrowBack, "backIcon")
           }
       }
   )
       Text(text = "\n"+
               "\n" +
               "Welcome to LAPAZRESTURANT - Where Flavor Meets Tradition!\n" +
               "\n" +
               "OUR STORY\n" +
               "\n" +
               "At LAPAZ, we believe that food is not just sustenance; it's an experience. Our journey began with a passion for bringing the rich and diverse flavors of the world to a single table. Established in [Year], [Restaurant Name] has since become a culinary destination known for its commitment to quality, creativity, and community.\n" +
               "\n" +
               "OUR PHILOSOPHY\n" +
               "\n" +
               "Our philosophy is simple: to serve delicious, thoughtfully prepared dishes that reflect our dedication to exceptional ingredients, culinary innovation, and warm hospitality. We take pride in sourcing the freshest, locally-sourced ingredients to create dishes that tantalize your taste buds and leave you craving more.\n" +
               "\n" +
               "THE LAPAZ EXPERIENCE\n" +
               "\n" +
               "When you dine with us, you embark on a culinary journey that celebrates the art of cooking. Our talented chefs draw inspiration from global cuisines to craft a menu that showcases a fusion of flavors, all made from scratch with love and care. From the first bite to the last, we aim to create memorable dining moments that linger in your memory.\n" +
               "\n" +""
               
               )

   }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AboutScreenPreview() {
    ResturanttTheme {
        AboutScreen(rememberNavController())
    }
}