package com.muzaffar.weatherapiexample.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.muzaffar.weatherapiexample.R
import com.muzaffar.weatherapiexample.models.Weather
import com.muzaffar.weatherapiexample.utils.Utility
import com.muzaffar.weatherapiexample.utils.Utility.Companion.convertKelvinToCelsius


@Composable
fun WeatherItem(weather: Weather) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ){
            AsyncImage(
                model = Utility.convertIdToImageUrl(weather.weather[0].icon),
                contentDescription = "Weather Icon"
            )

            Column(
                modifier = Modifier.padding(16.dp).weight(3f)
            ) {
                Text(text = "${Utility.convertTimestampToDate(weather.dt)}",fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Temperature: ${convertKelvinToCelsius(weather.temp.day)}°C", fontSize = 16.sp)
                Text(text = "Humidity: ${weather.humidity}%", fontSize = 16.sp)
                Text(text = "Pressure: ${weather.pressure} hPa", fontSize = 16.sp)
                Text(text = "Weather: ${weather.weather[0].description}", fontSize = 16.sp)
                Text(text = "Sunrise: ${Utility.convertTimestampToDate(weather.sunrise)}", fontSize = 16.sp)
                Text(text = "Sunset: ${Utility.convertTimestampToDate(weather.sunset)}", fontSize = 16.sp)

            }
        }
    }
}