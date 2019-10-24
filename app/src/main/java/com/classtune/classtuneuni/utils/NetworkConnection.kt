package com.classtune.classtuneuni.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

class NetworkConnection {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
//    internal var context: Context? = null
////    val instance = NetworkConnection()
//
//    companion object {
//        var instance: NetworkConnection = NetworkConnection()
//
//    }
//
////    private fun NetworkConnection(): ??? {}
//
////    fun getInstance(): NetworkConnection {
////        return instance
////    }
//
//    fun isNetworkAvailable(): Boolean {
//        try {
//            val cm = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            //NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            val networkInfo = cm.activeNetwork
//            //return networkInfo.isConnected();
//            if (networkInfo != null && networkInfo.isConnected) {
//                return true
//            }
//        } catch (e: Exception) {
//            return false
//        }
//
//        return false
//    }
}