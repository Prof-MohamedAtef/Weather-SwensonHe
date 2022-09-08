package mo.atef.swensonhe.weatherapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.FragmentSearchBinding
import mo.atef.swensonhe.weatherapp.interfaces.OnArrowPressed
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.view.MainActivity

class SearchViewFragment: Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var onArrowPressed:OnArrowPressed

    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var suggestedList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        suggestedList= ArrayList()
        suggestedList.add("See")
        suggestedList.add("Mohamed")
        suggestedList.add("Mohsen")
        suggestedList.add("Momken")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)
        Log.i("SearchViewFragment", "SearchView is called ...")

        onArrowPressed= Util.Companion.MainActivity!!
        binding.arrowIcon.setOnClickListener { view:View->
            onArrowPressed.showAppBar()
        }
        populateListView()
        binding.editTextSearchBox.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search","BeforeText is called")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search","onText is called")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("Search","afterText is called")
                listAdapter.filter.filter(p0)
            }
        })



        return binding.root
    }

    fun newInstance(mainActivity: MainActivity): Fragment? {
        Util.Companion.MainActivity=mainActivity
        return SearchViewFragment()
    }

    fun populateListView(){
        listAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,suggestedList)
        binding.listView.adapter=listAdapter
    }
}