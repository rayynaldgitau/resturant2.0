package com.raynaldgitaz.resturantt.ui.theme.pages.foodview

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raynaldgitaz.resturantt.R
import com.raynaldgitaz.resturantt.data.FoodRepository
import com.raynaldgitaz.resturantt.ui.theme.ResturanttTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(navController:NavHostController,modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background3), contentDescription ="Background", contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Add food",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
        var foodName by remember { mutableStateOf(TextFieldValue("")) }
        var foodDescription by remember { mutableStateOf(TextFieldValue("")) }
        var foodPrice by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text(text = "Food name ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = foodDescription,
            onValueChange = { foodDescription = it },
            label = { Text(text = "Description") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = foodPrice,
            onValueChange = { foodPrice = it },
            label = { Text(text = "Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var foodRepository = FoodRepository(navController,context)
            foodRepository.saveFood(foodName.text.trim(), foodDescription.text.trim(),
                foodPrice.text)
        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(20.dp))



        ImagePicker(modifier,context, navController, foodName.text.trim(), foodDescription.text.trim(), foodPrice.text.trim())



    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, description:String, price:String) {
    var hasImage by remember { mutableStateOf(false) }
    var image by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            image = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && image != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,image)
            Image(bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier
                    .size(200.dp)
                .padding(30.dp,10.dp,10.dp)
                )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var foodRepository = FoodRepository(navController,context)
                foodRepository.saveFoodWithImage(name, description, price,image!!)


            }) {
                Text(text = "Upload")
            }
        }
    }
}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)

@Composable
fun AddFoodScreenPreview() {
    ResturanttTheme {
        AddFoodScreen(rememberNavController())
    }
}