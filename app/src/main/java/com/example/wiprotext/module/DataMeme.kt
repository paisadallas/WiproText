package com.example.wiprotext.module

import com.google.gson.annotations.SerializedName

data class DataMeme(
    @SerializedName("memes")
    var meme: List<Meme>? = arrayListOf()
)
