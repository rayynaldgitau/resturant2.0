package com.raynaldgitaz.resturantt.ui.theme.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.raynaldgitaz.resturantt.navigation.ROUTE_HOME
import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavHostController) {
    LaunchedEffect(key1 = true){
        delay(2000L)
        navController.navigate(ROUTE_HOME)
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.splash3),
            contentDescription ="Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()

        )
    }

}


@Preview
@Composable
fun SplashPreview() {

    ResturanttTheme {
        Splash(rememberNavController())

    }
}