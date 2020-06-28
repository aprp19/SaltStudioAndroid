package com.aprp.saltstudioandroid.Walktrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.aprp.saltstudioandroid.MainActivity
import com.aprp.saltstudioandroid.R
import kotlinx.android.synthetic.main.activity_walktrough.*

class WalktroughActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walktrough)

        W_ViewPager.adapter = WalktroughAdapter()
        W_ViewPager.offscreenPageLimit = 3
        W_dots.attachViewPager(W_ViewPager)

        W_ViewPager.onPageSelected{
            val colorRes = when (it) {
                0 -> R.color.White
                1 -> R.color.White
                2 -> R.color.White
                else -> R.color.White
            }
            val color = ContextCompat.getColor(this, colorRes)
            W_Frame.setBackgroundColor(color)
            W_dots.setDotTintRes(if(color.isColorLight()) R.color.Black else R.color.White)
        }
    }
    fun skip(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}