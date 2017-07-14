package com.swarawan.geniekotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by rioswarawan on 7/12/17.
 */
class GenieApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}