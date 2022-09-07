package mo.atef.swensonhe.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mo.atef.swensonhe.weatherapp.models.WeatherModel
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class WeatherViewModel constructor(private val repository: WeatherRepository):ViewModel() {
    val weatherList=MutableLiveData<List<WeatherModel>>()
    val errorMessage=MutableLiveData<String>()

    fun getWeatherData(){
        val response=repository.getWeatherData()
        response.enqueue(object : retrofit2.Callback<List<WeatherModel>> {
            override fun onResponse(call: Call<List<WeatherModel>>, response: Response<List<WeatherModel>>) {
                weatherList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}