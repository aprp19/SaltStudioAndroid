package com.aprp.saltstudioandroid.Admin

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.aprp.saltstudioandroid.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        add_addbtn.setOnClickListener {
            confirmAdd()
        }

        admin_add_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            admin_add_selectedimage.setBackgroundDrawable(bitmapDrawable)
            admin_add_image.alpha = 0f
        }
    }

    private fun confirmAdd() {
        uploadImage()
    }

    private fun uploadImage(){
        if(selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/game_image/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("UploadImage","Upload Success: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d("UploadImage","File Location: $it")

                    saveToDatabase(it.toString())
                }
            }
    }

    private fun saveToDatabase(game_image: String) {
        val game_name = input_game_name.text.toString()
        val game_platform = input_game_platform.text.toString()
        val game_price = input_game_price.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("/Game/$game_name")

        val gameData = GameData(game_name, game_platform, game_price, game_image)
        ref.setValue(gameData)
            .addOnSuccessListener {
                Log.d("AddedGame","Added Success")
            }
    }
}

class GameData(val game_name: String, val game_platform: String,
           val game_price: String, val game_image: String)