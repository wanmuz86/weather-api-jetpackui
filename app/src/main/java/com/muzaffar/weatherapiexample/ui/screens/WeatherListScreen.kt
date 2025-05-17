package com.muzaffar.weatherapiexample.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.muzaffar.weatherapiexample.ui.components.WeatherItem
import com.muzaffar.weatherapiexample.viewmodel.WeatherViewModel

@Composable
fun WeatherListScreen(viewModel: WeatherViewModel = viewModel () , modifier: Modifier = Modifier) {
    // Initialize why observe/aware/listen to the data [ Observer, Listen - Observer Design Pattern]

    val weatherList by viewModel.weathers.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    var cityName by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier.fillMaxSize() ){
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(2f).padding(8.dp),
                value = cityName,
                onValueChange = {
                    cityName = it;
                },
                label = { Text("Enter City Name") }
            )
            Button(modifier= Modifier.weight(1f).padding(8.dp), onClick = {
                if (cityName.text != ""){
                    viewModel.getWeather(cityName.text)
                }else {
                    // TODO show snackbar
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color.Blue,
                contentColor = androidx.compose.ui.graphics.Color.White
            )) {
                Text("Search")
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when  {
                isLoading  -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align  (Alignment.Center)
                    )
                }
                errorMessage != null -> {
                    Text(
                        text = "Error: $errorMessage",
                        modifier = Modifier.align(Alignment.Center)
                    )
            }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        // Foreach item in the list
                        // I will show the weather in the WeatherItem
                        items(weatherList) {
                            weather ->
                            WeatherItem(weather = weather)
                        }
                    }
                }
            }

        }
    }

}