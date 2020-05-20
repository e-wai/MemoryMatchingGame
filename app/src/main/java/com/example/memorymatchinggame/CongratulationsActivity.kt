package com.example.memorymatchinggame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CongratulationsActivity : AppCompatActivity() {

    lateinit var returnBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulate)

        supportActionBar?.hide()
        returnBtn = findViewById(R.id.returnToHomeBtn)
        returnBtn.setOnClickListener{v -> this.finish()}
    }
}