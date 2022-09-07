package mo.atef.swensonhe.weatherapp.repositories

import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService

class WeatherRepository constructor(private val retrofitService:RetrofitService) {
    fun getWeatherData() = retrofitService.makeWeatherRequest()
}