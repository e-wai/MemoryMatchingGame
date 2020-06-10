package com.example.memorymatchinggame

import android.os.CountDownTimer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memorymatchinggame.model.Card
import com.example.memorymatchinggame.model.Game
import com.example.memorymatchinggame.viewmodel.CardsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.initMocks

class CardsViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Mock
    lateinit var timer: CountDownTimer

    @InjectMocks
    lateinit var viewModel: CardsViewModel

    @Before
    fun setUp() {
        timer = mock(CountDownTimer::class.java)
        `when`(timer.start()).thenReturn(null)

        MockitoAnnotations.initMocks(this)
        viewModel = CardsViewModel()
    }

    @Test
    fun callingStartGame_initializesScoreToZero_timeTo60() {
        var mockCard1 = mock(Card::class.java)
        var mockCard2 = mock(Card::class.java)
        viewModel.startGame(mutableListOf(mockCard1, mockCard2))

        assert(viewModel.getCards() == mutableListOf(mockCard1, mockCard2))
    }

}