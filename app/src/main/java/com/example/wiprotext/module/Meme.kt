package com.example.wiprotext.module

import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("id")
    var id: String,
    var name: String,
    var url: String?
)
