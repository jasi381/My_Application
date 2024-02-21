package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.data.Reponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


fun parseJsonFromAsset(context: Context): Reponse? {
    val json = try {
        val inputStream = context.assets.open("item_data.json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val text = reader.readText()
        inputStream.close()
        text
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
    return try {
        val gson = Gson()
        gson.fromJson(json, Reponse::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
