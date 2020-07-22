package com.aprp.saltstudioandroid.MainGame

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.aprp.saltstudioandroid.MainActivity
import com.aprp.saltstudioandroid.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_rmd.*
import java.io.File


class RmdActivity : AppCompatActivity() {

    lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rmd)

        storage = Firebase.storage

        rmd_bckbtn.setOnClickListener {
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }

        rmd_platform_android.setOnClickListener {
            Toast.makeText(this,"Android",Toast.LENGTH_SHORT).show()
        }

        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.rmd_trailer)
        lifecycle.addObserver(youTubePlayerView)

        rmd_psdl.setOnClickListener {
            Toast.makeText(this,"Not Yet Available",Toast.LENGTH_SHORT).show()
        }

        rmd_mldl.setOnClickListener {
            isStoragePermissionGranted()
        }
    }

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                Log.v("firebase", "Permission is granted")
                downloadFile()
                true
            } else {
                Log.v("firebase", "Permission is revoked")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("firebase", "Permission is granted")
            downloadFile()
            true
        }
    }

    private fun downloadFile() {
        val storage = FirebaseStorage.getInstance()
        val storageRef =
            storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/saltstudioandroid.appspot.com/o/game_utama%2FRMDv0.1.apk?alt=media&token=dc67608c-359f-4965-9b6a-ad0bcce3ffba")
        val pd = ProgressDialog(this)
        pd.setTitle("RMDv0.1.apk")
        pd.setMessage("Downloading Please Wait!")
        pd.isIndeterminate = true
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd.show()
        val rootPath =
            File(Environment.getExternalStorageDirectory(),"SaltStudioAndroid")
        if (!rootPath.exists()) {
            rootPath.mkdirs()
        }
        val localFile = File(rootPath, "RMDv0.1.apk")
        storageRef.getFile(localFile).addOnSuccessListener {
            Log.e(
                "firebase ",
                ";local tem file created  created $localFile"
            )
            if (!pd.isShowing){
                return@addOnSuccessListener
            }
            if (localFile.canRead()) {
                pd.dismiss()
            }
            Toast.makeText(applicationContext, "Download Completed", Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext, "Internal Storage/SaltStudioAndroid/RMDv0.1.apk", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { exception ->
            Log.e(
                "firebase ",
                ";local tem file not created $exception"
            )
            Toast.makeText(applicationContext, "Download Incompleted", Toast.LENGTH_LONG).show()
            pd.hide()
        }
    }
}