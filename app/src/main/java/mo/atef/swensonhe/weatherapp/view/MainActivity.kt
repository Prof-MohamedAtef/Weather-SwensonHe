package mo.atef.swensonhe.weatherapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.ActivityMainBinding
import mo.atef.swensonhe.weatherapp.fragments.AppBarFragment
import mo.atef.swensonhe.weatherapp.fragments.SearchViewFragment
import mo.atef.swensonhe.weatherapp.interfaces.OnArrowPressed
import mo.atef.swensonhe.weatherapp.interfaces.OnSearchPressed
import mo.atef.swensonhe.weatherapp.interfaces.OnSearchResultSelected
import mo.atef.swensonhe.weatherapp.models.WeatherModel
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModel
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModelFactory


class MainActivity : AppCompatActivity(),OnArrowPressed, OnSearchPressed, OnSearchResultSelected {
    lateinit var MyApiKey: String
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:WeatherViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var searchViewFragment:SearchViewFragment
    lateinit var appBarFragment: AppBarFragment

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Util.applyImmersiveMode(hasFocus,window.decorView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApiKey = mo.atef.swensonhe.weatherapp.BuildConfig.WeatherApiKey

        viewModel = ViewModelProvider(this,WeatherViewModelFactory(WeatherRepository(retrofitService))).get(WeatherViewModel::class.java)

        viewModel.getWeatherData(Util.Companion.CITY, MyApiKey)

        viewModel.weatherList.observe(this, Observer { weatherList ->
            bindWeatherData(weatherList)
        })

        viewModel.errorMessage.observe(this, Observer { error ->
            Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
        })

        searchViewFragment = SearchViewFragment()
        appBarFragment = AppBarFragment()

        appBarFragment.newInstance(this)?.let {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(
                    R.id.searchFrame,
                    it
                ).commitAllowingStateLoss()

        }
    }

    private fun bindWeatherData(weatherList: WeatherModel) {
        binding.tvCity.text = weatherList.location.name
        binding.tvDate.text = weatherList.location.localtime
        binding.tvCelcious.text = weatherList.current.temp_f.toString()
        binding.tvDayDescription.text = weatherList.current.condition.text
        binding.tvWind.text = weatherList.current.wind_mph.toString()
        binding.tvWaterPercentage.text = weatherList.current.humidity.toString()
        binding.tvSunnyToday.text =
            weatherList.current.temp_f.toString() + "/" + weatherList.current.humidity.toString()
        binding.tvRainyTomorrow.text =
            weatherList.current.temp_f.toString() + "/" + weatherList.current.humidity.toString()
        binding.tvRainyFriday.text =
            weatherList.current.temp_f.toString() + "/" + weatherList.current.humidity.toString()
    }

    override fun showAppBar() {
        appBarFragment.newInstance(this)?.let {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(
                    R.id.searchFrame,
                    it
                ).commitAllowingStateLoss()
        };
    }

    override fun showSearchBar() {
        searchViewFragment.newInstance(this)?.let {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(
                    R.id.searchFrame,
                    it
                ).commitAllowingStateLoss()
        }
    }

    override fun onSearchResultSelected(weatherModel: WeatherModel) {
        bindWeatherData(weatherModel)
    }
}