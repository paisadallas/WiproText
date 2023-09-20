package com.example.wiprotext.repository

import com.example.wiprotext.module.Meme
import com.example.wiprotext.module.MemesRespond
import io.reactivex.Observable


interface MemeRepository {

    fun getMemes(): Observable<MemesRespond>
}