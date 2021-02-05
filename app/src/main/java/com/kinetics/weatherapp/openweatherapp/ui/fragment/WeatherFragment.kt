package com.kinetics.weatherapp.openweatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.observe
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kinetics.weatherapp.openweatherapp.R
import com.kinetics.weatherapp.openweatherapp.databinding.FragmentWeatherBinding
import com.google.android.material.snackbar.Snackbar
import com.kinetics.weatherapp.openweatherapp.ui.WeatherViewModel
import com.kinetics.weatherapp.openweatherapp.utils.Utilities
import com.kinetics.weatherapp.openweatherapp.utils.actionBar
import com.kinetics.weatherapp.openweatherapp.utils.toolbarTitle
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class WeatherFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: Factory

    private val weatherViewmodel: WeatherViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentWeatherBinding

    private val isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false).apply {
            viewModel = weatherViewmodel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener(this)
        setActionBarTitle()
    }

    private fun setActionBarTitle() {
        weatherViewmodel.weatherLiveData.observe(this) {
            actionBar?.toolbarTitle = "${it.data?.name}, " +
                    "${it.data?.sys?.country}"
        }
    }

    private fun showSnackBar(message: String, listener: OnClickListener) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, listener)
                .show()
    }

    private fun isConnected(): Boolean {
        return if (!Utilities.isOnline(requireContext())) {
            showSnackBar(getString(R.string.no_internet), OnClickListener {
                snackRetryAction()
            })
            false
        } else true
    }

    private fun snackRetryAction() {
        if (isConnected()) {
            retryFetch()
        }
        isConnected()
    }

    private fun retryFetch() {
        weatherViewmodel.fetchData()
    }

    override fun onRefresh() {
        if (isConnected()) {
            retryFetch()
            binding.swipeRefresh.isRefreshing = isLoading
        }
        binding.swipeRefresh.isRefreshing = false
    }
}