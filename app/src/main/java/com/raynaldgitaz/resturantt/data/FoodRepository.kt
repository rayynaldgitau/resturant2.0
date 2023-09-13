package com.raynaldgitaz.resturantt.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.raynaldgitaz.resturantt.models.Food
import com.raynaldgitaz.resturantt.models.Upload
import com.raynaldgitaz.resturantt.navigation.ROUTE_LOGIN

class FoodRepository(var navController: NavHostController,var context: Context) {
    var authRepository:AuthRepository
    var progress: ProgressDialog

    init {
        authRepository = AuthRepository(navController,context)
        if (!authRepository.isLoggedIn()){
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveFood(foodName:String, foodDescription:String, foodPrice:String){
        var id = System.currentTimeMillis().toString()
        var foodData = Food(foodName,foodDescription,foodPrice,id)
        var foodRef = FirebaseDatabase.getInstance().getReference()
            .child("Foods/$id")
        progress.show()
        foodRef.setValue(foodData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewfoods(food: MutableState<Food>, foods: SnapshotStateList<Food>): SnapshotStateList<Food> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Foods")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                foods.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Food::class.java)
                    food.value = value!!
                    foods.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return foods
    }

    fun deleteFood(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Foods/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Food deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateFood(name:String, description:String, price:String,id:String){
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Foods/$id")
        progress.show()
        var updateData = Food(name, description, price, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    //IMAGE UPLOAD//


    fun saveFoodWithImage(foodName:String, foodDescription: String, foodPrice: String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var image = it.toString()
                    var houseData = Upload(image,foodName,foodDescription,
                        foodPrice,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun viewUploads(upload:MutableState<Upload>, uploads:SnapshotStateList<Upload>): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }



}