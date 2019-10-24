package com.classtune.classtuneuni.utils

import android.content.SharedPreferences

class AppSharedPreference {
    companion object{
        val keyModelTestPrefs = "modelTestPrefs"

        private val keyUserHome = "home"
        private val keyUserActive = "is_active"
        private val keyUserImg = "img"
        private val keyId = "id"
        private val keyUserId = "userId"
        private val keyIsFirstTime = "isFirstTime"
        private val keyIsFirsLogin = "isFirstLogin"
        private val keyUserName = "username"
        private val keyUserEmail = "useremail"
        private val keyUserPhone = "phone"
        private val keyUserPassword = "userpassword"
        private val keyApiKey = "api_key"
        private val keyRememberMe = "rememberMe"
        private val keyUDID = "udid"
        private val keyFCMId = "fcm_id"
        private val keyUserType = "userType"
        private val keyTabSt = "tab_st"
        private val keyCourseSt = "course_st"

        private fun getSharedPreferences(): SharedPreferences {
            return MyApplication.context.getSharedPreferences(keyModelTestPrefs, 0)
        }

        fun clearData() {
            val pref = getSharedPreferences()
            val editor = pref.edit()
            editor.clear()
            editor.apply()
        }
        fun getUsingFirstTime(): Boolean {
            val pref = getSharedPreferences()
            return pref.getBoolean(keyIsFirstTime, true)
        }
        fun setUsingFirstTime(isFirstTime: Boolean) {
            val pref = getSharedPreferences()
            val editor = pref.edit()

            editor.putBoolean(keyIsFirstTime, isFirstTime)
            editor.apply()
        }
        fun getUsingHomeFirstTime(): Boolean {
            val pref = getSharedPreferences()
            return pref.getBoolean(keyIsFirstTime, false)
        }
        fun setUsingHomeFirstTime(isFirstTime: Boolean) {
            val pref = getSharedPreferences()
            val editor = pref.edit()

            editor.putBoolean(keyIsFirstTime, isFirstTime)
            editor.apply()
        }

        fun getFirstTimeLogin(): Boolean {
            val pref = getSharedPreferences()
            return pref.getBoolean(keyIsFirsLogin, true)
        }

        fun setFirstTimeLogin(isFirstTime: Boolean) {
            val pref = getSharedPreferences()
            val editor = pref.edit()

            editor.putBoolean(keyIsFirsLogin, isFirstTime)
            editor.apply()
        }

        fun savePrefBoolean(key: String, value: Boolean) {
            val pref = getSharedPreferences()
            val editor = pref.edit()
            editor.putBoolean(key, value)
            editor.commit()
        }

        fun setFcm(fcm_id: String) {
            val pref = getSharedPreferences()
            val editor = pref.edit()

            editor.putString(keyFCMId, fcm_id)
            editor.apply()
        }

        fun getFcm(): String? {
            val pref = getSharedPreferences()
            return pref.getString(keyFCMId, "")
        }

    }
}