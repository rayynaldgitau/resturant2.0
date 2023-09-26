package com.raynaldgitaz.resturantt.ui.theme.pages.beta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.raynaldgitaz.resturantt.data.DrinksRepository
import com.raynaldgitaz.resturantt.models.Drink


@Composable
fun ViewDrinksScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var drinkRepository = DrinksRepository(navController, context)


        val emptyDrinkState = remember { mutableStateOf(Drink("","","","")) }
        var emptyDrinksListState = remember { mutableStateListOf<Drink>() }

        var drinks = drinkRepository.viewdrinks(emptyDrinkState, emptyDrinksListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           // Text(text = "All Drinks",
             //   fontSize = 30.sp,
              //  fontFamily = FontFamily.Cursive,
                //color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(drinks){
                    DrinkItem(
                        name = it.name,
                        description = it.description,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        drinkRepository = drinkRepository
                    )
                }
            }
        }
    }
}


@Composable
fun DrinkItem(name:String, description:String, price:String, id:String,
             navController:NavHostController, drinkRepository: DrinksRepository
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = description)
        Text(text = price)
        Button(onClick = {
            drinkRepository.deleteDrink(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate("")
        }) {
            Text(text = "Update")
        }
    }
}