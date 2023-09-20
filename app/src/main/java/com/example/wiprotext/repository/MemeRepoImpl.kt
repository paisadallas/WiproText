package com.example.wiprotext.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wiprotext.module.MemesRespond
import io.reactivex.Observable


class MemeRepoImpl(
    //private val fbiApi: FbiApi
    private val api:API
) : MemeRepository {

    override fun getMemes(): Observable<MemesRespond> {
        return api.getMemes()
    }
}