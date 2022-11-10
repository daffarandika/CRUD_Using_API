package com.example.basehttp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basehttp.databinding.ActivityEditHeroBinding
import com.example.basehttp.model.SuperHeroModel
import com.example.basehttp.utils.Constants
import com.example.basehttp.utils.Helper
import org.json.JSONObject

class EditActivity : AppCompatActivity(){
    private val TAG = "EditActivity"
    lateinit var binding : ActivityEditHeroBinding
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnEdit.setOnClickListener{
        }
        if (intent.hasExtra("id")){
            Log.d(TAG, "onCreate: punya")
            val id = intent.getStringExtra("id").toString()
            Helper.GetAt(id, Constants.superHeroes)
            setupUI(Constants.superHeroes)
            binding.btnEdit.setOnClickListener{
                val jsonObject = JSONObject()
                jsonObject.put("id", id)
                jsonObject.put("name", binding.edtEditName.text)
                jsonObject.put("firstName", binding.edtEditFirstName.text)
                jsonObject.put("lastName", binding.edtEditLastName.text)
                jsonObject.put("place", binding.edtEditPlace.text)
                val jsonString = jsonObject.toString()
                Helper.Put(jsonString)
                finish()
                Log.d(TAG, "onCreate: ${jsonString}")
                startActivity(Intent(this, MainActivity::class.java))
            }
        } else {
            binding.btnEdit.setOnClickListener{
                val toast = Toast.makeText(this, "heronya gak ketemu", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
    fun setupUI(superHeroes: MutableList<SuperHeroModel>) {
        binding.edtEditName.setText(superHeroes.first().name)
        binding.edtEditFirstName.setText(superHeroes.first().firstName)
        binding.edtEditLastName.setText(superHeroes.first().lastName)
        binding.edtEditPlace.setText(superHeroes.first().place)
    }

}