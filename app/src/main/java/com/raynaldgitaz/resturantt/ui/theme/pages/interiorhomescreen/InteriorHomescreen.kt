package com.raynaldgitaz.resturantt.ui.theme.pages.interiorhomescreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raynaldgitaz.resturantt.R
import com.raynaldgitaz.resturantt.data.FoodRepository
import com.raynaldgitaz.resturantt.navigation.ROUTE_ABOUT
import com.raynaldgitaz.resturantt.navigation.ROUTE_ADD_DRINKS
import com.raynaldgitaz.resturantt.navigation.ROUTE_ADD_FOOD
import com.raynaldgitaz.resturantt.navigation.ROUTE_LOGIN
import com.raynaldgitaz.resturantt.navigation.ROUTE_SIGNUP
import com.raynaldgitaz.resturantt.navigation.ROUTE_VIEW_DRINKS
import com.raynaldgitaz.resturantt.navigation.ROUTE_VIEW_FOOD
import com.raynaldgitaz.resturantt.navigation.ROUTE_VIEW_UPLOADS
import com.raynaldgitaz.resturantt.navigation.ROUTE_VIEW_UPLOAD_DRINKS
import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme

@Composable
fun InteriorHomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background2), contentDescription ="Background", contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(140.dp))

        Text(
            text = "Welcome to Lapaz",
            fontSize = 50.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.White,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(140.dp))


        Button(onClick = {
            navController.navigate(ROUTE_VIEW_UPLOADS)
        }) {
            Text(text = "View Menu")
        }

        Button(onClick = {
            navController.navigate(ROUTE_ADD_FOOD)
        }) {
            Text(text = "Add foods")
        }
        Button(onClick = {
            navController.navigate(ROUTE_ABOUT)
        }) {
            Text(text = "About")
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InteriorHomeScreenPreview() {
    ResturanttTheme {
        InteriorHomeScreen(rememberNavController())
    }
}