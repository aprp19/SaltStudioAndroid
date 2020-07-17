package com.aprp.saltstudioandroid.Menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.aprp.saltstudioandroid.Admin.GameData
import com.aprp.saltstudioandroid.R
import com.google.firebase.database.*

class ShopFragment : Fragment() {

    lateinit var list : MutableList<GameData>
    lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ref = FirebaseDatabase.getInstance().reference.child("Game")
        list = mutableListOf()
        listView = view.findViewById(R.id.shop_listView)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children){
                        val game = h.getValue(GameData::class.java)
                        list.add(game!!)
                    }
                    val adapter = ShopAdapter(context!!.applicationContext,R.layout.game_list,list)
                    listView.adapter = adapter
                }
            }
        })
    }
    companion object {
        fun newInstance(): ShopFragment{
            return ShopFragment()
        }
    }
}