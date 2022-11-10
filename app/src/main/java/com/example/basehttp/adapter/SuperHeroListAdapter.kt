package com.example.basehttp.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.basehttp.EditActivity
import com.example.basehttp.MainActivity
import com.example.basehttp.R
import com.example.basehttp.model.SuperHeroModel
import com.example.basehttp.utils.Helper
import com.example.basehttp.utils.Helper.Helper.Get

class SuperHeroListAdapter(val context: Context, val superHeroList: MutableList<SuperHeroModel>) : BaseAdapter(){
    private val TAG = "SuperHeroListAdapter"
    override fun getCount() = superHeroList.size

    override fun getItem(p0: Int): Any = superHeroList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.super_hero_list_item, p2, false)
        val resourses = context.resources
        val superHero = superHeroList[p0]
        val textID = view.findViewById<TextView>(R.id.text_id)
        val textName = view.findViewById<TextView>(R.id.text_name)
        val textFirstName = view.findViewById<TextView>(R.id.text_first_name)
        val textLastName = view.findViewById<TextView>(R.id.text_last_name)
        val textPlace = view.findViewById<TextView>(R.id.text_place)

        textID.text = resourses.getString(R.string.id_1_s, superHero.id)
        textName.text = resourses.getString(R.string.name_1_s, superHero.name)
        textFirstName.text = resourses.getString(R.string.first_name_1_s, superHero.firstName)
        textLastName.text = resourses.getString(R.string.last_name_1_s, superHero.lastName)
        textPlace.text = resourses.getString(R.string.place_1_s, superHero.place)

        val remove = view.findViewById<ImageButton>(R.id.btn_remove)
        val edit = view.findViewById<ImageButton>(R.id.btn_edit)
        remove.setOnClickListener{
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete")
            alertDialog.setMessage("Sure want to delete this hero ?")
            alertDialog.setPositiveButton("yes") { dialog, id ->
                Helper.Delete(superHero.id)
                (context as Activity).finish()
                context.startActivity(Intent(context, MainActivity::class.java))
            }
            alertDialog.setNegativeButton("no") {dialog, id ->
                dialog.dismiss()
            }
            alertDialog.show()
        }
        edit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            val id = superHero.id
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
        return view
    }
}