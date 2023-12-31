package com.raynaldgitaz.resturantt.ui.theme.pages.foodview

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.raynaldgitaz.resturantt.R
import com.raynaldgitaz.resturantt.data.FoodRepository
import com.raynaldgitaz.resturantt.models.Upload
import com.raynaldgitaz.resturantt.navigation.ROUTE_INTERIOR_HOMESCREEN
import com.raynaldgitaz.resturantt.navigation.ROUTE_UPDATE_FOOD
import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewUploadsScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background2), contentDescription ="Background", contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var foodRepository = FoodRepository(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var uploads = foodRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                    TopAppBar(
                        title = {
                            Text(text = "Menu",
                                    modifier = Modifier.padding(100.dp,10.dp,10.dp),
                                fontSize = 40.sp,
                                color = Color.White)
                        },
                        navigationIcon = {
                            IconButton(onClick = {navController.navigate(ROUTE_INTERIOR_HOMESCREEN)}) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")
                            }
                        }
                    )



            Spacer(modifier = Modifier.height(20.dp))

            val sections = (0 until 25).toList().chunked(5)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(uploads){
                    UploadItem(
                        image = it.image,
                        name = it.name,
                        description = it.description,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        foodRepository = foodRepository
                    )
                }

            }


        }
    }
}






@Composable
fun UploadItem(image:String,name:String, description:String, price:String, id:String,
               navController:NavHostController, foodRepository:FoodRepository) {

    val paddingModifier  = Modifier.padding(10.dp)
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .size(width = 240.dp, height = 300.dp)
    ){

        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(195.dp)
                .clip(RoundedCornerShape(10))

        )
        Text(text = name,
            modifier = Modifier.padding(30.dp,5.dp,10.dp),)
        Text(text = description,
            modifier = Modifier.padding(30.dp,5.dp,10.dp),)
        Text(text = price,
            modifier = Modifier.padding(30.dp,5.dp,10.dp),)
        Row {


            Button(
                onClick = {
                    foodRepository.deleteFood(id)
                },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Delete")
            }
            Button(onClick = {
                navController.navigate("$ROUTE_UPDATE_FOOD/$id")
            }) {
                Text(text = "Update")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ViewUploadsScreenPreview() {
    ResturanttTheme {
        ViewUploadsScreen(rememberNavController())
    }
}