package com.aprp.saltstudioandroid.Menu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.aprp.saltstudioandroid.Admin.GameData
import com.aprp.saltstudioandroid.R
import com.squareup.picasso.Picasso


class ShopAdapter (val mCtx: Context, val layoutResId: Int, val gamelist: List<GameData>)
    : ArrayAdapter<GameData>(mCtx,layoutResId,gamelist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val list_namagame = view.findViewById<TextView>(R.id.list_nama_game)
        val list_platformgame = view.findViewById<TextView>(R.id.list_platform_game)
        val list_hargagame = view.findViewById<TextView>(R.id.list_harga_game)
        val list_imggame = view.findViewById<ImageView>(R.id.list_img_game)
        val list_buygame = view.findViewById<CardView>(R.id.list_buybtn)

        val game = gamelist[position]

        list_namagame.text = game.game_name
        list_platformgame.text = game.game_platform
        list_hargagame.text = game.game_price
        Picasso.get().load(game.game_image).into(list_imggame)
        list_buygame.setOnClickListener {
            val number = "+6285156380969"
            val text = "Permisi, Mau Beli " + game.game_name + "dengan harga : " + game.game_price
            val url = "https://api.whatsapp.com/send?phone=$number&text=$text"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(mCtx,i,Bundle.EMPTY)
        }

        return view
    }
}