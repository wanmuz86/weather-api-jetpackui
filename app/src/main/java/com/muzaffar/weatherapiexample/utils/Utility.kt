package com.muzaffar.weatherapiexample.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utility {

    companion object {
        fun convertTimestampToDate(
            timestamp: Long,
            pattern: String = "yyyy-MM-dd HH:mm:ss"
        ): String {
            val date = Date(timestamp * 1000) // Multiply by 1000 to convert seconds to milliseconds
            val format = SimpleDateFormat(pattern, Locale.getDefault())
            return format.format(date)
        }
        fun convertKelvinToCelsius(kelvin: Double): Double {
            return String.format("%.2f", kelvin - 273.15).toDouble()
        }
        fun convertIdToImageUrl(iconId:String):String {
            return "https://openweathermap.org/img/wn/$iconId@2x.png"
        }
    }
}