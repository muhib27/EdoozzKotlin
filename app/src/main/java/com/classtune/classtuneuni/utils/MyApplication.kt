package com.classtune.classtuneuni.utils

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex

class MyApplication: Application() {
//    lateinit var mInstance: MyApplication
//    private var context: Context? = null
    //ApiInterface apiInterface;


    val NOTIFICATION_CHANNEL_ID = "10001"

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        fun sendMyNotification(get: String, get1: String, get2: String, get3: String, get4: String) {

        }

        lateinit var instance: MyApplication
        lateinit var context: Context
//            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this
    }
}