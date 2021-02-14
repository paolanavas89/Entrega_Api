package com.example.entrega_api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


class DogsManager {
//desde aqui manda la información al viewModel
    companion object {
        suspend fun getAllDogs() : List<Dogs> {
            val client = OkHttpClient()
            val url = "https://random.dog/woof.json"
            val request = Request.Builder()
                    .url(url)
                    .build()

            var listaURL = mutableListOf<Dogs>()
            repeat(10){
                val call = client.newCall(request)
                val exc=call.execute()
                val jsonData =  exc.body?.string()
                val jsonObject = JSONObject(jsonData)
                val dog = Dogs(fileSizeBytes = jsonObject.getInt("fileSizeBytes"),url = jsonObject.getString("url"))
                listaURL.add(dog)
                exc.close()
                Log.w("getAllDog",dog.url)
            }

            return listaURL

        }

    }
}