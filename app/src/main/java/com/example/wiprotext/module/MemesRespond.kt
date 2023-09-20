package com.example.wiprotext.module

import com.google.gson.annotations.SerializedName

data class MemesRespond(
    @SerializedName("success")
    val success: String,
    @SerializedName("data")
    val data: DataMeme
)
