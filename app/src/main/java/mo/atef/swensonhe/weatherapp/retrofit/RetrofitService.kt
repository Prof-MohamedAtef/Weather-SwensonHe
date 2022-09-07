package mo.atef.swensonhe.weatherapp.retrofit


import mo.atef.swensonhe.weatherapp.models.WeatherModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("v1/current.json?")
    fun makeWeatherRequest() : Call<List<WeatherModel>>

    companion object{
        var retrofitService:RetrofitService?=null

        fun getInstance():RetrofitService{
            if(retrofitService==null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.weatherapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}