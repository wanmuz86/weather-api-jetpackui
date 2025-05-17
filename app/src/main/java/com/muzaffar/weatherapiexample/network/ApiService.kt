package com.muzaffar.weatherapiexample.network

import com.muzaffar.weatherapiexample.models.Weather
import com.muzaffar.weatherapiexample.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // API URL based on my API Call

    // https://api.openweathermap.org/data/2.5/forecast/daily?q=kajang&appId=9fd7a449d055dba26a982a3220f32aa2
    // suspend - keyword untuk backfround method in kotlin coroutine
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("q") city: String,
                           @Query("appid") apiKey: String): WeatherResponse
}