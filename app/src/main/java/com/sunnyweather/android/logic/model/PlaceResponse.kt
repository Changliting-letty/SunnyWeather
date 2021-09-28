package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class  PlaceResponse(val status:String,val places:List<Place>)

//使用了@SerializedName注解的方式，来让JSON字段和Kotlin字段之间建立映射关系
data class  Place(val name:String,val location:Location,@SerializedName("formatted_address") val address:String)


data class Location(val lng:String,val lat:String)
