package mo.atef.swensonhe.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import mo.atef.swensonhe.weatherapp.R
import mo.atef.swensonhe.weatherapp.databinding.FragmentAppbarBinding
import mo.atef.swensonhe.weatherapp.interfaces.OnSearchPressed
import mo.atef.swensonhe.weatherapp.util.Util
import mo.atef.swensonhe.weatherapp.view.MainActivity

class AppBarFragment:Fragment() {
    lateinit var binding:FragmentAppbarBinding
    lateinit var onSearchPressed: OnSearchPressed


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appbar, container, false)
        Log.i("AppBarFragment", "AppBarFragment is called ...")

        onSearchPressed= Util.Companion.MainActivity!!

        binding.btnSearch?.setOnClickListener { view: View ->
            onSearchPressed.showSearchBar()

        }
        return binding.root
    }

    fun newInstance(mainActivity: MainActivity): Fragment? {
        Util.Companion.MainActivity=mainActivity
        return AppBarFragment()
    }
}