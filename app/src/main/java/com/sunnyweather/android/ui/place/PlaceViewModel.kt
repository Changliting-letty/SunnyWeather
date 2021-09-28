package com.sunnyweather.android.ui.place

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place

class PlaceViewModel:ViewModel() {
    private  val searchLiveData=MutableLiveData<String>()
    //缓存数据用于展示，保证旋转不丢失
    val placeList=ArrayList<Place>()
    //使用Transformations的switchMap()方法来观察这个对象
    val placeLiveData=Transformations.switchMap(searchLiveData){
        query -> Repository.searchPlaces(query)
    }
    fun searchPlaces(query: String){
        searchLiveData.value=query
    }
}