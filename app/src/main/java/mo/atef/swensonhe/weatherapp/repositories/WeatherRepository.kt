package mo.atef.swensonhe.weatherapp.repositories

import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService

class WeatherRepository constructor(private val retrofitService:RetrofitService) {
    fun getWeatherData(city: String, MyApiKey: String) = retrofitService.makeWeatherRequest(MyApiKey,city,"no","3","no")
}