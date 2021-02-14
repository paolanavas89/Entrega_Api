package com.example.entrega_api

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DogsAdapter : RecyclerView.Adapter<DogsAdapter.StringViewHolder>()  {

    private var datos : List<Dogs>? = null

    class StringViewHolder(val root: View, val imgVDogs: ImageView, val textView: TextView) : RecyclerView.ViewHolder(root)
    private lateinit var recyclerView : RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        val imgView = view.findViewById<ImageView>(R.id.imgVDogs)
        val textView = view.findViewById<TextView>(R.id.tex1)
        return StringViewHolder(view, imgView, textView)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {

        //si no es una imagen dara error y se pondrá el icono del video para que se vea que es un video
        datos?.let {
            Picasso.get()
                    .load(it[position].url)
                    .error(R.drawable.video)
                    .into(holder.imgVDogs);
        }
        holder.textView.setText("DOG_"+ (position + 1))
        //Se comunica con la segunda actividad
        holder.root.setOnClickListener {
            val context = it.context
            datos?.let {
                val extension = it[position].url.substringAfterLast('.', "")
                Log.e("aqui", extension)
                //si la extension es un video se pone el tipo 1 , sino, se pone el tipo 0 que es una imagen
                if (extension == "mp4"){
                    SecondActivityDogs.createSecondActivity(context, it[position].url, 1)
                }else {
                    SecondActivityDogs.createSecondActivity(context, it[position].url, 0)
                }

            }

        }


    }

    override fun getItemCount(): Int {
        datos?.let {
            return it.size
        }
        return 0
    }

    suspend fun setData(string : List<Dogs>){
        datos = string
        withContext(Dispatchers.Main){
            notifyDataSetChanged()
        }
    }



}