package com.mercari.retrofit

import android.content.Context

/**
 * Created by tangxutao on 2018/3/29.
 */
class RetrofigConfig {
    val BASE_API_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"

    fun getURL() :String {
        return BASE_API_URL
    }

    companion object {
         var instance: RetrofigConfig? = null

        fun getInstance(context: Context) : RetrofigConfig {
            if (instance == null) {
                instance = RetrofigConfig()
            }

            return instance as RetrofigConfig
        }
    }
}