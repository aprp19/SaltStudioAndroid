package com.aprp.saltstudioandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aprp.saltstudioandroid.Menu.AboutFragment
import com.aprp.saltstudioandroid.Menu.CartFragment
import com.aprp.saltstudioandroid.Menu.HomeFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(HomeFragment.newInstance())
        m_btm_nav.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        m_btm_nav.add(MeowBottomNavigation.Model(2,R.drawable.ic_cart))
        m_btm_nav.add(MeowBottomNavigation.Model(3,R.drawable.ic_aboutus))

        m_btm_nav.setOnClickMenuListener {
            when(it.id){
                1 -> setFragment(HomeFragment.newInstance())
                2 -> setFragment(CartFragment.newInstance())
                3 -> setFragment(AboutFragment.newInstance())
                else -> ""
            }
        }
        m_btm_nav.show(1)
    }

    fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.m_frame,fragment,"mainActivity")
            .commit()
    }
}