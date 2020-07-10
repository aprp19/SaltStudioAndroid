package com.aprp.saltstudioandroid.Menu

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aprp.saltstudioandroid.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.*


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return AboutPage(context)
            .isRTL(false)
            .setImage(R.drawable.ic_saltstudio)
            .setDescription(getString(R.string.about_desc))
            .addGroup("Contact Us")
            .addEmail("SaltGameStudio@Gmail.com","Email")
            .addItem(Copyright())
            .create()
//        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    fun Copyright(): Element {
        val CopyrightsElement = Element()
        val copyrights = String.format(
            getString(R.string.about_copyrights),
            Calendar.getInstance().get(Calendar.YEAR)
        )
        CopyrightsElement.setTitle(copyrights)
        CopyrightsElement.setAutoApplyIconTint(true)
        CopyrightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        CopyrightsElement.setIconNightTint(android.R.color.white)
        CopyrightsElement.setGravity(Gravity.CENTER)
        CopyrightsElement.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,copyrights,Toast.LENGTH_SHORT).show()
        })
        return CopyrightsElement
    }

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }
}
