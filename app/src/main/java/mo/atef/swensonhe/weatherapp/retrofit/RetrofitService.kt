package mo.atef.swensonhe.weatherapp.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import mo.atef.swensonhe.weatherapp.models.WeatherModel
import mo.atef.swensonhe.weatherapp.util.Util
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface RetrofitService {

    @GET("/v1/current.json?")
    fun makeWeatherRequest(
        @Query ("key") apiToken: String?,
        @Query("q") city:String?,
        @Query("days") days:String?,
        @Query("aqi") aqi:String?,
        @Query("alerts") alerts:String?
    ): Call<WeatherModel>

    companion object{
        var gson = GsonBuilder()
            .setLenient()
            .create()

        var retrofitService:RetrofitService?=null

        fun getInstance():RetrofitService{
            if(retrofitService==null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.weatherapi.com")
                    .client(getHeader())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

        fun getHeader(): OkHttpClient? {

            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.e("InterceptorMessage :", message);
                }
            })
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            //        okClient.interceptors().add()
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor { chain: Interceptor.Chain ->
                    var request: Request = chain.request()
                    val requestBuilder: Request.Builder = request.newBuilder()
                    requestBuilder.addHeader("Content-Type", Util.MULTIPART)
                    //                            requestBuilder.addHeader("Content-Type", "application/json");
                    requestBuilder.addHeader("Accept", "application/json")
                    request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()
        }
    }
}