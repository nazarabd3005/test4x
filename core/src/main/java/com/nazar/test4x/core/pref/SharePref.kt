package com.nazar.test4x.core.pref

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharePref @Inject constructor(val context: Context) {

    companion object {
        const val  IS_APP_LOCK_ACTIVE: String = "IS_APP_LOCK_ACTIVE"
        const val LOCK_PAUSE_TIME: String = "LOCK_PAUSE_TIME"
        const val MINUTES_TO_LOCK: String = "MINUTES_TO_LOCK"
        private const val APP_MAIN_PREF: String = "app_pref"

    }

    fun savePreferences(key: String?, value: String?) {
        val preferences = context.getSharedPreferences(
            APP_MAIN_PREF, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Method To Load Preferences
     *
     * @param key The string value of the preference to load
     * @returns value The string value of the key passed
     */
    fun loadPreferences(key: String?): String {
        var strValue: String = ""
        val preferences = context.getSharedPreferences(APP_MAIN_PREF, Context.MODE_PRIVATE)
        strValue = preferences.getString(key, "").toString()
        return strValue
    }

    fun saveBoolPreferences(key: String?, value: Boolean) {
        val preferences = context.getSharedPreferences(
            APP_MAIN_PREF, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun clearPreferences() {
        val preferences = context.getSharedPreferences(
            APP_MAIN_PREF, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    fun loadBoolPreferences(key: String?): Boolean {
        var value: Boolean
        val preferences = context.getSharedPreferences(
            APP_MAIN_PREF, Context.MODE_PRIVATE)
        value = preferences.getBoolean(key, false)
        return value
    }
}