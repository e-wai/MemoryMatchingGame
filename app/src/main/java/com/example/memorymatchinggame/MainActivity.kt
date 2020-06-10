package com.example.memorymatchinggame

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.example.memorymatchinggame.newGameMode"
const val GAME_RESULT = "com.example.memorymatchinggame.gameResult"

class MainActivity : AppCompatActivity() {

    lateinit var newGameEasyBtn: Button
    lateinit var newGameHardBtn: Button
    var gameStatus: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        newGameEasyBtn = findViewById(R.id.newGameEasyBtn)
        newGameEasyBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "EASY")
            }
            startActivityForResult(intent, gameStatus)
        }

        newGameHardBtn = findViewById(R.id.newGameHardBtn)
        newGameHardBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, GameActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "HARD")
            }
            startActivityForResult(intent, gameStatus)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == gameStatus) {
            if (resultCode == Activity.RESULT_OK) {
                val gameResult = data?.getStringExtra(GAME_RESULT)
                if(gameResult == "WON") {
                    val intent = Intent(this@MainActivity, CongratulationsActivity::class.java)
                    startActivity(intent)
                } else if (gameResult == "LOST") {
                    AlertDialog.Builder(this)
                        .setTitle("GAME LOST :(")
                        .setMessage("You ran out of time! Better luck next time")
                        .setNeutralButton("OK") {dialog, _ -> dialog.cancel()}
                        .show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED){
                val gameResult = data?.getStringExtra(GAME_RESULT)
                if(gameResult == "ERROR RETRIEVING FROM NETWORK") {
                    AlertDialog.Builder(this)
                        .setTitle("ERROR RETRIEVING FROM NETWORK")
                        .setMessage("Sorry! Try again later...")
                        .setNeutralButton("OK") {dialog, _ -> dialog.cancel()}
                        .show()
                }
            }

        }
    }
}
