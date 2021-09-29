package com.sunnyweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location

class WeatherViewModel:ViewModel() {
    private  val locationLiveData=MutableLiveData<Location>()
    //保证旋转界面数据不丢失
    var locationLng=""
    var locationLat=""
    var placeName=""

    //观察对象
    val weatherLiveData=Transformations.switchMap(locationLiveData){
        location->
        Repository.refreshWeather(locationLng,locationLat)
    }
    fun  refreshWeather(lng:String,lat:String){
        locationLiveData.value=Location(lng,lat)
    }
}