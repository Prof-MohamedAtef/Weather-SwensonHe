package mo.atef.swensonhe.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

    fun newInstance(mainActivity: MainActivity): Fragment? {
        Util.Companion.MainActivity=mainActivity
        return SearchViewFragment()
    }
}