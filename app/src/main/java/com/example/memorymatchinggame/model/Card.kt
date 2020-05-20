package com.example.memorymatchinggame.model

data class CardOptions (
    val products: List<Card>
)

data class Card (
    val id: Long,
    val images: List<ImageOptions> //only one element
) {
    var cardStatus: CardStatus = CardStatus.NOT_FOUND
        get() {
            if(field == null) {
                cardStatus = CardStatus.NOT_FOUND
            }
            return field
        }
}

data class ImageOptions (
    val src: String
)

enum class CardStatus {
    FOUND, NOT_FOUND, SELECTED, FLIP
}