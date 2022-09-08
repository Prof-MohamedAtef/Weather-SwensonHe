package mo.atef.swensonhe.weatherapp.interfaces

import mo.atef.swensonhe.weatherapp.models.WeatherModel

interface OnSearchResultSelected {
    fun onSearchResultSelected(weatherList: WeatherModel)
}