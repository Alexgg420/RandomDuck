package com.example.randomduck

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private val duckImages = arrayOf(R.drawable.duck1, R.drawable.duck2, R.drawable.duck3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        showRandomDuckImage()

        // OnClickListener para mostrar imágenes aleatorias una vez se haga click
        imageView.setOnClickListener {
            showRandomDuckImage()
        }
    }

    private fun showRandomDuckImage() {
        val randomIndex = Random.nextInt(duckImages.size)
        val randomDuckImage = duckImages[randomIndex]
        imageView.setImageResource(randomDuckImage)
    }
}
