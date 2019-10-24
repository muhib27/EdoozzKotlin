package com.classtune.classtuneuni.api

import com.classtune.classtuneuni.utils.URLHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {


    companion object {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
            .build()

        private fun getRetrofit(Url:String):Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Url)
                .client(okHttpClient)
                .build()
        }



        fun getApiData():Retrofit{
            val retrofitApi = getRetrofit(URLHelper.BASE_URL + URLHelper.SUB_URL)
            return retrofitApi
        }

        fun callApi():ApiInterface{
            val retrofitCall = getApiData()
            return retrofitCall.create(ApiInterface::class.java)
        }

    }

}