package com.mercari.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory



/**
 * Created by tangxutao on 2018/3/29.
 */
class RetrofitHelper {
    var mContext: Context? = null
    var mConfig: RetrofigConfig? = null
    var mRetrofit: Retrofit? = null

    var client = OkHttpClient()
    var factory = GsonConverterFactory.create(GsonBuilder().create())

    constructor(context:Context,config: RetrofigConfig){
        mContext = context
        mConfig = config

        getRetrofit()
    }

    companion object {
        lateinit var instance: RetrofitHelper

        fun init (context:Context, config: RetrofigConfig){
            instance = RetrofitHelper(context,config)
        }

        fun <T> createApi(clazz: Class<T>): T {
            if (instance == null) {
                throw RuntimeException("init RetrofitHelper frist!")
            }
            return instance.mRetrofit!!.create(clazz)
        }
    }

    fun getRetrofit() {
        mRetrofit = Retrofit.Builder()
                .baseUrl(mConfig!!.getURL())
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }
}