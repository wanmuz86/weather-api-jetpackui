package com.muzaffar.weatherapiexample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance
{
    private const val BASE_URL = "https://api.openweathermap.org/"

    // Instatiate and API service by lazy instation
//    The apiService variable will not be created immediately when your class or object is loaded.
//    It will be initialized the first time you access it (i.e., on first use).
//    After that, the same instance is reused every time. (Singleton)
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Use GSONConverter - Library to convert json to kotlin object
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // based on the ApiService interface that we created previously
            .create(ApiService::class.java)
    }
}