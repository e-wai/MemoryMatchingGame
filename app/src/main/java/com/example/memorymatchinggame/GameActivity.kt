package com.example.memorymatchinggame

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorymatchinggame.adapter.CardAdapter
import com.example.memorymatchinggame.databinding.ActivityGameBinding
import com.example.memorymatchinggame.model.Card
import com.example.memorymatchinggame.viewmodel.CardsViewModel


class GameActivity : AppCompatActivity(), CardAdapter.OnCardClickListener {

    lateinit var grid: RecyclerView
    lateinit var model: CardsViewModel

    override fun onCardClicked(position: Int) {
        model.cardSelected(position)
        grid.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this).get(CardsViewModel::class.java)
        model.mode = intent.getStringExtra(EXTRA_MESSAGE) ?: "EASY"

        model.getCards().observe(this, Observer<List<Card>> { cards ->
            grid.adapter = CardAdapter(this@GameActivity, cards, this)
        })
        model.getStatus().observe(this, Observer<Int> {
            if (it != 0) {
                gameCompleted(it)
            }
        })

        val binding = DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        binding.lifecycleOwner = this
        binding.viewmodel = model
        grid = findViewById(R.id.grid)

        supportActionBar?.hide()

        //TODO: check this
        val width: Float = Resources.getSystem().displayMetrics.heightPixels / Resources.getSystem().displayMetrics.density
        println("This is the $width")
        grid.layoutManager = GridLayoutManager(this, (width / 150).toInt())
    }

    fun gameCompleted(result: Int) {
        when(result) {
            1 -> {
                val intent = Intent().apply {
                    putExtra(GAME_RESULT, "WON")
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            2 -> {
                val intent = Intent().apply {
                    putExtra(GAME_RESULT, "ERROR RETRIEVING FROM NETWORK")
                }
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
        }
    }
}