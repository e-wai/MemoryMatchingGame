package com.example.memorymatchinggame.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorymatchinggame.GAME_RESULT
import com.example.memorymatchinggame.api.Api
import com.example.memorymatchinggame.api.URL
import com.example.memorymatchinggame.model.Card
import com.example.memorymatchinggame.model.CardOptions
import com.example.memorymatchinggame.model.Game
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CardsViewModel: ViewModel() {

    lateinit var cardsList: MutableLiveData<List<Card>>
    var _game: MutableLiveData<Game> = MutableLiveData()
    var _score: MutableLiveData<Int> = MutableLiveData()
    var score: LiveData<Int> = _score
    var mode: String = "EASY"
    var status: MutableLiveData<Int> = MutableLiveData()

    fun loadCards() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val call = api.getCardOptions()

        call.enqueue(object : Callback<CardOptions> {
            override fun onResponse(call: Call<CardOptions>, response: Response<CardOptions>) {

                val received = response.body()
                if (received != null)
                {
                    var tmp = received.products.subList(0, 10).toMutableList()
                    if (mode == "EASY") {
                        for (i in 0 until 10)
                        {
                            tmp.add(tmp[i].copy())
                        }
                    } else {
                        for (i in 0 until 10)
                        {
                            tmp.add(tmp[i].copy())
                            tmp.add(tmp[i].copy())
                        }
                    }
                    tmp.shuffle()
                    cardsList.value = tmp
                    _game.value = Game(tmp, if(mode == "HARD") Game.GameMode.HARD else Game.GameMode.EASY)

                    _score.value = _game.value?.matchesFound
                } else {
                    status.value = 2
                }
            }
            override fun onFailure(call: Call<CardOptions>, t: Throwable) {
                status.value = 2
            }
        })
    }

    fun getCards(): LiveData<List<Card>> {
        if (!this::cardsList.isInitialized || cardsList.value == null) {
            cardsList = MutableLiveData()
            loadCards()
        }

        return cardsList
    }

    fun cardSelected(position: Int) {
        when(_game.value?.cardSelected(position)) {
            true -> {
                _score.value = _game.value?.matchesFound
                if (_game.value?.checkResult() == true) {
                    status.value = 1
                }
            }
            false -> viewModelScope.launch{
                _game.value?.flipCards()
                delay(100)
                _game.value?.flipCards()
            }
        }
    }

    fun randomize() {
        _game.value?.shuffleCards()
        cardsList.value = _game.value?.cards?.toList()
    }

    fun getStatus(): LiveData<Int> {
        if (status.value == null) {
            status.value = 0
        }
        return status
    }
}