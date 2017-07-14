package com.swarawan.geniekotlin.database.local

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.text.TextUtils
import com.google.gson.Gson
import com.swarawan.geniekotlin.GenieApp

/**
 * Created by rioswarawan on 7/13/17.
 */
class CachePreference(preferenceName: String) {

    private var sharedPreference: SharedPreferences? = GenieApp.context!!.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    @Synchronized fun <T> read(key: String, tClass: Class<T>): Any? {
        var obj: Any? = null
        if (tClass == String::class.java) {
            obj = sharedPreference?.getString(key, "")
        } else if (tClass == Integer::class.java) {
            obj = sharedPreference?.getInt(key, 0)
        } else if (tClass == Boolean::class.java) {
            obj = sharedPreference?.getBoolean(key, false)
        } else if (tClass == Uri::class.java) {
            val uri: String? = sharedPreference?.getString(key, "")
            if (!TextUtils.isEmpty(uri))
                obj = Uri.parse(uri)
            else obj = ""
        }
        return obj
    }

    @Synchronized fun <T> write(key: String, value: T, tClass: Class<T>) {
        val editor: SharedPreferences.Editor = sharedPreference!!.edit()
        if (tClass == String::class.java)
            editor.putString(key, value.toString())
        else if (tClass == Integer::class.java)
            editor.putInt(key, value.toString().toInt())
        else if (tClass == Boolean::class.java)
            editor.putBoolean(key, value as Boolean)
        else if (tClass == Uri::class.java)
            if (!TextUtils.isEmpty(value.toString()))
                editor.putString(key, Gson().toJson(value.toString()))
        editor.apply()
    }

    @Synchronized fun clear() {
        val editor: SharedPreferences.Editor = sharedPreference!!.edit()
        editor.clear()
        editor.apply()
    }
}