package com.example.basehttp.utils

import com.example.basehttp.model.SuperHeroModel

class Constants {
    companion object Constants {
        val url : String = "https://394e-103-175-240-129.ap.ngrok.io/api/superhero"
        var superHeroes : MutableList<SuperHeroModel> = mutableListOf()
    }
}