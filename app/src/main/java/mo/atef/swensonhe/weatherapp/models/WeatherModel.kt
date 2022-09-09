package mo.atef.swensonhe.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    val location: Location
)