package com.muzaffar.weatherapiexample.models

import android.view.textclassifier.ConversationActions

data class WeatherResponse (
    val city: City,
    val list: List<Weather> = emptyList(),
    val cnt: Int,
    val message: String
)

data class Temp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Weather(
    val dt:Long,
    val sunrise:Long,
    val sunset:Long,
    val temp: Temp,
    val pressure: Int,
    val humidity: Int,
    val weather: List<WeatherInfo>

)

data class City (
   val id: Int,
    val name:String,
    val country:String

)
