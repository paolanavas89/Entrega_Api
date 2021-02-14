package com.example.entrega_api
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.entrega_api.databinding.ActivitySecondBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.squareup.picasso.Picasso

class SecondActivityDogs : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var videoPlayer: SimpleExoPlayer? = null
    private var sampleUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra(KEY)
        val type = intent.getIntExtra(KEY2,0)

        //le agrego la condición para q muestre la imagen o el video.
        if  (type == 0) {
            binding.img2.visibility = View.VISIBLE
            binding.videoPlayerView.visibility=View.GONE
                                    //le agrego una animación :)
            Picasso.get().load(url).placeholder( R.drawable.progress_animation ).error(R.drawable.video).into(binding.img2);

        }else  if  (type == 1) {
            binding.img2.visibility = View.GONE
            binding.videoPlayerView.visibility = View.VISIBLE


            url?.let {
                //si es un video se inicializa el video
                sampleUrl = url
                initPlayer()
            }

        }

    }
    //aqui llega la información del adapten
    companion object {

        private const val KEY = "URL"
        private const val KEY2 = "TYPE"

        fun createSecondActivity(context: Context, valor : String, typeFormat : Int) {
            val intent = Intent(context, SecondActivityDogs::class.java)
            intent.putExtra(KEY2 , typeFormat)
            intent.putExtra(KEY, valor)
            context.startActivity(intent)
        }
    }
 //funciones para cojer el video y pasarle la URL

    private fun initPlayer(){
        videoPlayer = SimpleExoPlayer.Builder(this).build()
        binding.videoPlayerView.player = videoPlayer
        buildMediaSource()?.let {
            videoPlayer?.prepare(it)
        }
    }

    private fun buildMediaSource(): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(sampleUrl))
    }



}