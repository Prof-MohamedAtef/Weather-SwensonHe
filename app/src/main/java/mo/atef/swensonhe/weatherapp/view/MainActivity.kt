package mo.atef.swensonhe.weatherapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.ActivityMainBinding
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModel
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var MyApiKey: String
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private val retrofitService = RetrofitService.getInstance()
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Util.applyImmersiveMode(hasFocus,window.decorView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.detailToolbar)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        MyApiKey= mo.atef.swensonhe.weatherapp.BuildConfig.WeatherApiKey

        viewModel=ViewModelProvider(this, WeatherViewModelFactory(WeatherRepository(retrofitService))).get(WeatherViewModel::class.java)
        viewModel.weatherList.observe(this, Observer { weatherList->

        })

        viewModel.errorMessage.observe(this, Observer { error->

        })

        binding.searchBar.btnSearch.setOnClickListener { view:View->

        }

        binding.appBar
    }


}