package com.example.wiprotext.repository

import com.example.wiprotext.module.MemesRespond
import io.reactivex.Observable

import retrofit2.http.GET

interface API {

    @GET("get_memes")
    fun getMemes(): Observable<MemesRespond>

    companion object {
        val BASE_URL: String = "https://api.imgflip.com/"
        }

}