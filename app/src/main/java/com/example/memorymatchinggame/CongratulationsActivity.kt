package com.example.memorymatchinggame

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CongratulationsActivity : AppCompatActivity() {

    lateinit var returnBtn: Button
    lateinit var trophyImage: ImageView
    val trophyImageSrc: String = "https://media1.tenor.com/images/1ea9b6b30492252e08c7cbd44d069fb1/tenor.gif?itemid=16466287"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulate)

        supportActionBar?.hide()
        returnBtn = findViewById(R.id.returnToHomeBtn)
        returnBtn.setOnClickListener{v -> this.finish()}

        trophyImage = findViewById(R.id.trophy)
        Glide.with(this)
            .load(trophyImageSrc)
            .into(trophyImage)
    }
}