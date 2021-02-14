package com.example.entrega_api


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrega_api.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var model :MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private var adapter = DogsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        createRecyclerView()

        GlobalScope.launch(Dispatchers.IO) {
            val resultados = model.getApiResults()
            adapter.setData(resultados)
        }

    }


    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }


    }





