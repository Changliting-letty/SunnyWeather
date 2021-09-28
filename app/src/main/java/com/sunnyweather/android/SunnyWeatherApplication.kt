package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

//获取全局context
class SunnyWeatherApplication :Application(){

    companion object {
        const val TOKEN="YjCBRHnvKO3eQZaH"
        @SuppressLint("StaticFieldLeak")
        lateinit var  context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}