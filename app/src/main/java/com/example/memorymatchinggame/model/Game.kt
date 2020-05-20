package com.example.memorymatchinggame.model

class Game (cards_to_use: MutableList<Card>, mode: GameMode) {
    var cardsInMatch: Int = if(mode == GameMode.EASY) 2 else 3
    var matchesFound: Int = 0
    var cards: MutableList<Card> = cards_to_use
    var cardsCurrentlySelected: MutableList<Card> = ArrayList()

    enum class GameMode {
        EASY, HARD
    }

    fun checkResult(): Boolean {
        return matchesFound == cards.size / cardsInMatch
    }

    fun cardSelected(position: Int): Boolean? {
        if(cards[position].cardStatus == CardStatus.FOUND || cards[position].cardStatus == CardStatus.SELECTED) {
            return null
        }

        cardsCurrentlySelected.add(cards[position])
        cards[position].cardStatus = CardStatus.SELECTED

        return if (cardsCurrentlySelected.size == cardsInMatch) checkForMatches() else null
    }

    private fun checkForMatches(): Boolean {
        if (cardsCurrentlySelected.all{it == cardsCurrentlySelected[0]}) {
            matchesFound ++
            cards.forEach{
                if (it == cardsCurrentlySelected[0]) {
                    it.cardStatus = CardStatus.FOUND
                }
            }
            cardsCurrentlySelected.clear()
            return true
        }

        cards.forEach{
            if (it.cardStatus == CardStatus.SELECTED) {
                it.cardStatus = CardStatus.FLIP
            }
        }
        cardsCurrentlySelected.clear()
        return false
    }

    fun shuffleCards() {
        cardsCurrentlySelected.clear()
        cards.shuffle()
    }

    fun flipCards() {
        cards.forEach {
            if(it.cardStatus == CardStatus.FLIP) {
                it.cardStatus = CardStatus.SELECTED
            } else if (it.cardStatus == CardStatus.SELECTED) {
                it.cardStatus = CardStatus.NOT_FOUND
            }
        }
    }
}