package com.example.memorymatchinggame

import com.example.memorymatchinggame.model.Card
import com.example.memorymatchinggame.model.CardStatus
import com.example.memorymatchinggame.model.Game
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GameTest {

    lateinit var mockCard1: Card
    lateinit var mockCard2: Card
    lateinit var testGame: Game

    @Before
    fun setUp() {
        mockCard1 = mock(Card::class.java)
        `when`(mockCard1.cardStatus).thenReturn(CardStatus.FOUND)
        mockCard2 = mock(Card::class.java)
        `when`(mockCard2.cardStatus).thenReturn(CardStatus.SELECTED)

        testGame = Game(mutableListOf(mockCard1, mockCard2), Game.GameMode.EASY)
    }

    @Test
    fun checkResult_returnsExpectedResult() {
        var testGame = Game(mutableListOf(mockCard1, mockCard2), Game.GameMode.EASY)
        assert(testGame.matchesFound == 0)
        testGame.matchesFound++
        assert(testGame.checkResult())
        testGame.matchesFound--
        assert(!testGame.checkResult())
    }

    @Test
    fun cardSelected_returnsNullIfCardsCurrentlySelectedNotFull() {
        assert(testGame.cardsCurrentlySelected.isEmpty())
        assert(testGame.cardSelected(0) == null)
    }

    @Test
    fun cardSelected_returnsNullIfCardAtPositionIsAlreadySELECTEDOrFOUND() {
        testGame.cardsCurrentlySelected.add(mockCard1)
        assert(testGame.cardSelected(0) == null)
        testGame.cardsCurrentlySelected.add(mockCard2)
        assert(testGame.cardSelected(1) == null)
    }

    @Test
    fun cardSelected_ifValidCardAddedToSelectedArrayandChangesCardStatus() {
        `when`(mockCard1.cardStatus).thenReturn(CardStatus.NOT_FOUND)
        testGame.cardSelected(0)

        assertEquals(testGame.cardsCurrentlySelected, mutableListOf(mockCard1))
        verify(mockCard1).cardStatus = CardStatus.SELECTED
    }

    @Test
    fun shuffleCards_clearsArray() {
        testGame.shuffleCards()
        assert(testGame.cardsCurrentlySelected.isEmpty())
    }

    @Test
    fun flipCards_changesFLIPtoSELECTEDandSELECTEDtoNOT_FOUND() {
        val mockCard3 = mock(Card::class.java)
        `when`(mockCard3.cardStatus).thenReturn(CardStatus.FLIP)
        val mockCard4 = mock(Card::class.java)
        `when`(mockCard4.cardStatus).thenReturn(CardStatus.NOT_FOUND)

        //mockCard1 - FOUND, mockCard2 - SELECTED
        `when`(mockCard1.cardStatus).thenReturn(CardStatus.FOUND)
        `when`(mockCard2.cardStatus).thenReturn(CardStatus.SELECTED)

        val busierTestGame = Game(mutableListOf(mockCard1, mockCard2, mockCard3, mockCard4), Game.GameMode.EASY)
        busierTestGame.flipCards()

        verify(mockCard1, times(0)).cardStatus = CardStatus.FOUND
        verify(mockCard2).cardStatus = CardStatus.NOT_FOUND
        verify(mockCard3).cardStatus = CardStatus.SELECTED
        verify(mockCard4, times(0)).cardStatus = CardStatus.NOT_FOUND
    }
}