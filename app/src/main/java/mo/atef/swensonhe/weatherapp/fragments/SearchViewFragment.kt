package mo.atef.swensonhe.weatherapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.FragmentSearchBinding
import mo.atef.swensonhe.weatherapp.interfaces.OnArrowPressed
import mo.atef.swensonhe.weatherapp.interfaces.OnSearchResultSelected
import mo.atef.swensonhe.weatherapp.models.WeatherModel
import mo.atef.swensonhe.weatherapp.repositories.WeatherRepository
import mo.atef.swensonhe.weatherapp.retrofit.RetrofitService
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.view.MainActivity
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModel
import mo.atef.swensonhe.weatherapp.viewmodel.WeatherViewModelFactory

class SearchViewFragment: Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var onArrowPressed:OnArrowPressed
    lateinit var onSearchResultSelected: OnSearchResultSelected

    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var suggestedList: ArrayList<String>

    private lateinit var viewModel:WeatherViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var MyApiKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApiKey = mo.atef.swensonhe.weatherapp.BuildConfig.WeatherApiKey
        suggestedList= ArrayList()
        suggestedList.add("Cairo")
        suggestedList.add("Los Anglos")
        suggestedList.add("New York")
        suggestedList.add(("Washington"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)
        Log.i("SearchViewFragment", "SearchView is called ...")
        listAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,suggestedList)
        manipulateUI()

        viewModel = ViewModelProvider(this,
            WeatherViewModelFactory(WeatherRepository(retrofitService))
        ).get(WeatherViewModel::class.java)



        viewModel.weatherList.observe(viewLifecycleOwner, Observer { weatherList ->
            if (weatherList!=null){
                populateListView(weatherList)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireActivity(),error.toString(), Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

    private fun manipulateUI() {
        onArrowPressed = Util.Companion.MainActivity!!
        onSearchResultSelected = Util.Companion.MainActivity!!
        binding.arrowIcon.setOnClickListener { view: View ->
            onArrowPressed.showAppBar()
        }
        binding.editTextSearchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "BeforeText is called")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "onText is called")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("Search", "afterText is called")
                listAdapter.filter.filter(p0)
                val query = p0.toString()

                if (findChar(query, suggestedList)) {
                    return
                } else {
                    viewModel.getWeatherData(p0.toString(), MyApiKey)
                }
            }
        })
    }

    fun findMatch(s: String, strings: List<String>): Boolean {
        return strings.any { s.contains(it) }
    }

    fun findChar(s:String, strings:List<String>):Boolean{
        return s.all { bItem ->
            strings.any { it.contains(bItem) }
        }
    }

    fun newInstance(mainActivity: MainActivity): Fragment? {
        Util.Companion.MainActivity=mainActivity
        return SearchViewFragment()
    }

    fun populateListView(weatherList: WeatherModel) {
        if (findMatch(weatherList.location.name , suggestedList)){
            return
        }else{
            suggestedList.add(weatherList.location.name)
            listAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,suggestedList)
            binding.listView.adapter=listAdapter
            if (suggestedList.size>0){
                binding.dragUp.isVisible=true
            }
            binding.listView.setOnItemClickListener { adapterView, view, i, l ->
                binding.editTextSearchBox.setText(adapterView.getItemAtPosition(i).toString())
                onSearchResultSelected.onSearchResultSelected(weatherList)
            }

            binding.dragUp.setOnClickListener { view:View->
                onArrowPressed.showAppBar()
            }
        }
    }
    // TODO: pedning change edittext layout in portrait layout
}