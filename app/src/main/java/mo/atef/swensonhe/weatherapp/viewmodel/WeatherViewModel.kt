package mo.atef.swensonhe.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mo.atef.swensonhe.weatherapp.models.WeatherModel
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import retrofit2.Call
import retrofit2.Response

class WeatherViewModel constructor(private val repository: WeatherRepository):ViewModel() {
    val weatherList=MutableLiveData<WeatherModel>()
    val errorMessage=MutableLiveData<String>()

    fun getWeatherData(city: String, MyApiKey: String) {
        val response = repository.getWeatherData(city, MyApiKey)
        response.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                weatherList.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}