package com.example.wiprotext.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiprotext.module.MemesRespond
import com.example.wiprotext.repository.API
import com.example.wiprotext.repository.Network
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainActivityViewModel: ViewModel() {

    val network : Network = Network()
    val gson : Gson = network.provideGson()
    val loggingInterceptor: HttpLoggingInterceptor = network.loggingInterceptor()
    val okHttpClient: OkHttpClient = network.okHttpClient(loggingInterceptor)
    val retrofitAPI: API = network.retrofitAPI(okHttpClient, gson)


    private val _apiData: MutableLiveData<MemesRespond> = MutableLiveData()
    val api: LiveData<MemesRespond> = _apiData

    fun callApi(){
        retrofitAPI.getMemes()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                Log.d("API_MEME",it.data.meme!![0].name)
                    _apiData.postValue(it)
                },{
                    Log.d("API_MEME","Error $it")
                })
    }

}