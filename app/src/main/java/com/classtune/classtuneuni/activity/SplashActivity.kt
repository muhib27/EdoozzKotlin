package com.classtune.classtuneuni.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.classtune.classtuneuni.R
import com.classtune.classtuneuni.api.ApiClient
import com.classtune.classtuneuni.utils.AppSharedPreference
import com.classtune.classtuneuni.utils.NetworkConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.alert_dialog_no_connectivity.*
import kotlinx.android.synthetic.main.alert_dialog_no_connectivity.view.*

class SplashActivity : AppCompatActivity() {
    internal var SPLASH_TIME_OUT = 3000
    internal var flag = false
    internal var liveApp = true
    internal var regid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        if (!NetworkConnection.isNetworkAvailable(applicationContext)) {
            flag = true
             openDialog()
            return
        }


        val tokenReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val token = intent.getStringExtra("token")

                if (token != null) {
                    Log.e("firebase", token.toString())
                    regid = token
                    if (AppSharedPreference.getFcm() != null) {
                        AppSharedPreference.setFcm(regid)
                        sendRegistrationIdToBackend(regid)
                    }

                    // send token to your server
                }

            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            tokenReceiver,
            IntentFilter("tokenReceiver")
        )
    }


    fun sendRegistrationIdToBackend(token: String) {
        if (!NetworkConnection.isNetworkAvailable(applicationContext)) {
            flag = true
            return
        }
        ApiClient.callApi().addFcm(token)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                {
                    result(it.status.code.toString(), it.status.msg.toString())
                },
                {
                    flag = true
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })
    }

    private fun result(code: String, msg: String) {
        if (code.equals("200")) {
              navigateToNextPage()
        }
        // AppSharedPreference.setUserNameAndPassword(username, password);
        flag = true
    }

    fun navigateToNextPage() {

        val handler = Handler()

        handler.postDelayed(
            {
                // make sure we close the splash screen so the user won't come back when it presses back key

                //                finish();

                //                if (!mIsBackButtonPressed) {
                val isFirstTime = AppSharedPreference.getUsingFirstTime()
                val intent: Intent
                if (liveApp) {
                    flag = true
                    if (isFirstTime) {
                        intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    } else {
                        intent = Intent(this@SplashActivity, MainActivity::class.java)
                    }
                    startActivity(intent)
                    //                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish()
                } else {
                    finish()
                }
            },
            SPLASH_TIME_OUT.toLong()
        )// time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called

    }

    fun openDialog() {


        val factory = LayoutInflater.from(this@SplashActivity)
        val dialogView = factory.inflate(R.layout.alert_dialog_no_connectivity, null)

        // val acceptBtn = deleteDialogView.findViewById(R.id.ok) as Button

        val deleteDialog = AlertDialog.Builder(this@SplashActivity).create()
        deleteDialog.setCancelable(false)
        if (deleteDialog.isShowing())
            deleteDialog.dismiss()
        deleteDialog.setView(dialogView)
        dialogView.ok.setOnClickListener {
            //your business logic

            if (AppSharedPreference.getFcm() != null)
                sendRegistrationIdToBackend(AppSharedPreference.getFcm()!!)
            deleteDialog.dismiss()
        }
        dialogView.cancel.setOnClickListener(View.OnClickListener {
            //your business logic
            deleteDialog.dismiss()
            finish()
        })
        deleteDialog.show()
    }

    override fun onBackPressed() {
        if (flag) {
            super.onBackPressed()
            liveApp = false
        }
    }
}
