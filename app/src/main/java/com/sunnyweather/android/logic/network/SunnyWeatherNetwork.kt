package com.sunnyweather.android.logic.network

import android.app.DownloadManager
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

object SunnyWeatherNetwork{
    //天气信息
    private  val weatherService=ServiceCreator.create<WeatherService>()

    suspend fun getDailyWeather(lng:String,lat:String)= weatherService.getDailyWeather(lng,lat).await()

    suspend fun getRealtimeWeather(lng:String,lat:String)= weatherService.getRealtimeWeather(lng,lat).await()
    //下边是城市信息


    private  val placeService=ServiceCreator.create<PlaceService>()  //接口的动态代理对象

    suspend fun searchPlaces(query: String)= placeService.searchPlaces(query).await()  //定义函数调用接口中的相关方法

//回调函数
    private suspend fun <T> Call<T>.await():T{
        return  suspendCoroutine {
            continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if (body!=null) {

                        continuation.resume(body)
                    }
                    else {
                        continuation.resumeWithException(RuntimeException("Response body is null"))
                        Log.d("main","Response body is null")
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                 continuation.resumeWithException(t)
                }
            })
        }
    }
}