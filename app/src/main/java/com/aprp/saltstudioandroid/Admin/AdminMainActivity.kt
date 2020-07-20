package com.aprp.saltstudioandroid.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aprp.saltstudioandroid.Menu.ShopFragment
import com.aprp.saltstudioandroid.R
import kotlinx.android.synthetic.main.activity_admin_main.*

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        admin_bckbtn.setOnClickListener { startActivity(Intent(this,ShopFragment::class.java)) }
        admin_addbtn.setOnClickListener { startActivity(Intent(this,AddActivity::class.java)) }
    }
}