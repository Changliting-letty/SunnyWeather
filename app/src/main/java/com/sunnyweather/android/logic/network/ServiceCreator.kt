package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator{
    private  const val  BASE_URL="https://api.caiyunapp.com/"

    private   val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)   //知名Retrofut请求的根路径
        .addConverterFactory(GsonConverterFactory.create())  //用到的转换库
        .build()


    fun  <T> create(serviceClass:Class<T>):T= retrofit.create(serviceClass)  //动态代理对象

    inline fun <reified  T> create():T= create(T::class.java)  //用到了泛型实化



}