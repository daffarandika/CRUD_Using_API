package com.example.basehttp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.basehttp.adapter.SuperHeroListAdapter
import com.example.basehttp.databinding.ActivityMainBinding
import com.example.basehttp.model.SuperHeroModel
import com.example.basehttp.utils.Constants
import com.example.basehttp.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding : ActivityMainBinding
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Helper.Get(Constants.superHeroes)
        val adapter = SuperHeroListAdapter(this, Constants.superHeroes)
        binding.listView.adapter = adapter

    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
        Helper.Get(Constants.superHeroes)
    }
}