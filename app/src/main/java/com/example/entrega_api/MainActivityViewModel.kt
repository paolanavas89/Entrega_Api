package com.example.entrega_api

import android.app.DownloadManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

//pasamos los objetos a la vista
    suspend fun getApiResults() : List<Dogs>  {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                //hago la llamada al metodo para pintar el resultado
               DogsManager.getAllDogs()
            }
            resultado.await()
        }
    }
}