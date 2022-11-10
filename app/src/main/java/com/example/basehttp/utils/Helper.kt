package com.example.basehttp.utils

import android.util.Log
import android.widget.Toast
import com.example.basehttp.model.SuperHeroModel
import com.example.basehttp.utils.Constants.Constants.superHeroes
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class Helper {
    companion object Helper {
        private val TAG = "Helper"
        fun Put(jsonString : String) = runBlocking{
            launch (Dispatchers.IO){
                val conn = URL(Constants.url).openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.requestMethod = "PUT"
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-type", "application/json")

                val outputStreamWriter = OutputStreamWriter(conn.outputStream)
                outputStreamWriter.write(jsonString)
                outputStreamWriter.flush()
                Log.d(TAG, "Put: ${conn.inputStream}")
            }
        }
        fun Delete(id : String) = runBlocking{
            launch (Dispatchers.IO){
                val conn = URL(Constants.url+"/"+id).openConnection() as HttpURLConnection
                conn.requestMethod = "DELETE"
                conn.doInput = true
                conn.doOutput = false
                
                if (conn.responseCode == 200){
                }
            }
        }
        fun Get(superHeroes : MutableList<SuperHeroModel>) = runBlocking{
            launch(Dispatchers.IO){
                val conn = URL(Constants.url).openConnection() as HttpURLConnection
                val jsonString = conn.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(jsonString)
                superHeroes.clear()
                for (i in 0 until jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(i)
                    superHeroes.add( SuperHeroModel(
                        jsonObject.getString("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("firstName"),
                        jsonObject.getString("lastName"),
                        jsonObject.getString("place")
                    ))
                }
            }
        }
        fun Post(jsonString: String) = runBlocking{
            launch(Dispatchers.IO){

                val conn = URL(Constants.url).openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.doInput = true
                conn.requestMethod = "POST"
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-type", "application/json")

                val outputStreamWriter = OutputStreamWriter(conn.outputStream)
                outputStreamWriter.write(jsonString)
                outputStreamWriter.flush()

                if (conn.responseCode == 200){
                } else {
                }
            }
        }

         fun GetAt(id: String, superHeroes:MutableList<SuperHeroModel>) = runBlocking{
             launch(Dispatchers.IO) {
                 val conn = URL(Constants.url+"/"+id).openConnection() as HttpURLConnection
                 val jsonString = conn.inputStream.bufferedReader().readText()
                 val jsonObject = JSONObject(jsonString)
                 superHeroes.clear()
                 superHeroes.add(SuperHeroModel(
                     jsonObject.getString("id"),
                     jsonObject.getString("name"),
                     jsonObject.getString("firstName"),
                     jsonObject.getString("lastName"),
                     jsonObject.getString("place")
                 ))
             }
        }
    }
}