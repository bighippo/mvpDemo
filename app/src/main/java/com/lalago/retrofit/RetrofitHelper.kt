package com.lalago.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory



/**
 * Created by tangxutao on 2018/7/30.
 */
class RetrofitHelper {
    private var mConfig: RetrofigConfig? = null
    private var mRetrofit: Retrofit? = null
    private var mClient = OkHttpClient()
    private var mFactory = GsonConverterFactory.create(GsonBuilder().create())

    constructor(config: RetrofigConfig){

        mConfig = config

        getRetrofit()

    }

    companion object {
        private lateinit var instance: RetrofitHelper

        fun init (config: RetrofigConfig){
            instance = RetrofitHelper(config)
        }

        fun <T> createApi(clazz: Class<T>): T {
            if (instance == null) {
                throw RuntimeException("init RetrofitHelper frist!")
            }
            return instance.mRetrofit!!.create(clazz)
        }
    }

    private fun getRetrofit() {
        mRetrofit = Retrofit.Builder()
                .baseUrl(mConfig!!.getURL())
                .client(mClient)
                .addConverterFactory(mFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }
}