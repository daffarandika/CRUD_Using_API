package com.example.basehttp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.UnusedAppRestrictionsConstants
import com.example.basehttp.databinding.ActivityInsertHeroBinding
import com.example.basehttp.utils.Constants
import com.example.basehttp.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class InsertActivity : AppCompatActivity(){
    lateinit var binding : ActivityInsertHeroBinding
    private val TAG = "InsertActivity"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnInsert.setOnClickListener{
            val name = binding.edtInsertName.text.toString()
            val firstName = binding.edtInsertFirstName.text.toString()
            val lastName = binding.edtInsertLastName.text.toString()
            val place = binding.edtInsertPlace.text.toString()

            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("firstName", firstName)
            jsonObject.put("lastName", lastName)
            jsonObject.put("place", place)

            val jsonObjectString = jsonObject.toString()
            Helper.Post(jsonObjectString)
            finish()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

}