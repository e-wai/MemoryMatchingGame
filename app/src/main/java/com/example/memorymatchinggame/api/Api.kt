package com.example.memorymatchinggame.api

import com.example.memorymatchinggame.model.CardOptions
import retrofit2.Call
import retrofit2.http.GET

const val URL = "https://shopicruit.myshopify.com/admin/"

interface Api {
    @GET("products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun getCardOptions(): Call<CardOptions>
}