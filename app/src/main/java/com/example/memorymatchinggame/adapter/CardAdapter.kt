package com.example.memorymatchinggame.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.memorymatchinggame.model.CardOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memorymatchinggame.R
import com.example.memorymatchinggame.model.Card
import com.example.memorymatchinggame.model.CardStatus
import kotlinx.coroutines.delay


class CardAdapter(context: Context, cards: List<Card>, onCardClickListener: OnCardClickListener): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var context = context
    var cards = cards
    var onCardClickListener: OnCardClickListener = onCardClickListener

    override fun onBindViewHolder(holder: CardAdapter.CardViewHolder, position: Int) {
        val card: Card = cards[position]

        when (card.cardStatus) {
            CardStatus.FOUND -> {
                Glide.with(context)
                    .load(card.images[0].src)
                    .apply(RequestOptions().placeholder(R.drawable.card_cover))
                    .into(holder.imageView)
                holder.imageView.setColorFilter(Color.GRAY)
            }
            CardStatus.SELECTED, CardStatus.FLIP -> {
                holder.imageView.colorFilter = null
                Glide.with(context)
                    .load(card.images[0].src)
                    .apply(RequestOptions().placeholder(R.drawable.card_cover))
                    .into(holder.imageView)
            }
            CardStatus.NOT_FOUND -> {
                holder.imageView.colorFilter = null
                holder.imageView.setImageResource(R.drawable.card_cover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        val view = LayoutInflater.from(context).inflate(com.example.memorymatchinggame.R.layout.card, parent, false)
        return CardViewHolder(view, onCardClickListener)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class CardViewHolder(itemView: View, onCardClickListener: OnCardClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageView: ImageView = itemView.findViewById(com.example.memorymatchinggame.R.id.imageView)
        var onCardClickListener: OnCardClickListener = onCardClickListener

        init {
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onCardClickListener.onCardClicked(adapterPosition)
        }
    }

    interface OnCardClickListener {
        fun onCardClicked(position: Int)
    }
}