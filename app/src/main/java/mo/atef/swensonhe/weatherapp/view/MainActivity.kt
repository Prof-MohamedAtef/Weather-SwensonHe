package mo.atef.swensonhe.weatherapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.ActivityMainBinding
import mo.atef.swensonhe.weatherapp.fragments.AppBarFragment
import mo.atef.swensonhe.weatherapp.fragments.SearchViewFragment
import mo.atef.swensonhe.weatherapp.interfaces.OnArrowPressed
import mo.atef.swensonhe.weatherapp.interfaces.OnSearchPressed
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModel
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModelFactory


class MainActivity : AppCompatActivity(),OnArrowPressed, OnSearchPressed {
    lateinit var MyApiKey: String
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
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

        viewModel = ViewModelProvider(
            this,
            WeatherViewModelFactory(WeatherRepository(retrofitService))
        ).get(WeatherViewModel::class.java)
        viewModel.weatherList.observe(this, Observer { weatherList ->

        })

        viewModel.errorMessage.observe(this, Observer { error ->

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
}