package com.muzaffar.weatherapiexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzaffar.weatherapiexample.models.Weather
import com.muzaffar.weatherapiexample.network.RetrofitInstance
import kotlinx.coroutines.launch

// ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment.
// Logic to get and manage the data - Logic TBC - FROM API
class WeatherViewModel: ViewModel() {
    val apiKey = "9fd7a449d055dba26a982a3220f32aa2"

    //LiveData is an Android architecture component that is used to observe changes in data.
    // and update / refresh the UI
    // replaces notifyDataSetChanged() method in adapter [RecyclerView]
    // Mutable means that the data can be changed
    private val _weathers = MutableLiveData<List<Weather>>()
    // public variable  to be accessed by the View/UI
    val weathers: LiveData<List<Weather>> = _weathers

    // private variable that is used to observe the loading state on API call
    // and update/refresh the UI accordingly
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // private variable that is used to observe an error message on the API
    // and update/refresh the UI accordingly
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage


    fun  getWeather(city: String){
        // Run asynchronous process in background thread
        // in viewModelScope - ViewModel lifecycle scope
        // we run getWeather function which has suspend keyword
        // means - asynchronous process
        viewModelScope.launch {
            // set isLoading - true
            _isLoading.value = true
            try {
                // Retrieve the weather,
                // The weather will be replied as WeatherResponse
                // if get it , retrieeve the list from weatherResponse
                // and set the value to _weathers

               val response = RetrofitInstance.apiService.getWeather(city, apiKey)
                _weathers.value = response.list
                // set error to null
                _errorMessage.value = null
            }
            catch (e: Exception){
                // set error to error message
                _errorMessage.value = e.message
            }
            finally {
                // stop the loading
                _isLoading.value = false
            }

        }

    }
}