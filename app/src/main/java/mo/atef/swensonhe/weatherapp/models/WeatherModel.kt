package mo.atef.swensonhe.weatherapp.models

data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)