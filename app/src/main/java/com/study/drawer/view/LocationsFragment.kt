package com.study.drawer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.study.drawer.R
import com.study.drawer.contracts.BaseContract
import com.study.drawer.databinding.FragmentLocationsBinding
import com.study.drawer.model.LocationResult
import com.study.drawer.prensetation.LocationPresenter
import com.study.drawer.util.Utils
import com.study.drawer.view.adapters.LocationAdapter

class LocationsFragment: Fragment(R.layout.fragment_locations), BaseContract.View<List<LocationResult>> {

    private var _binding:FragmentLocationsBinding? = null;
    private val binding get() = _binding!!

    val presenter = LocationPresenter()

    private val locationList:MutableList<LocationResult> = ArrayList()
    lateinit var adapter:LocationAdapter

    private lateinit var progress:ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLocationsBinding.bind(view)
        presenter.view = this
        progress = binding.progressLocation
        adapter = LocationAdapter(locationList) {seeResidents(it)}

        if(adapter.itemCount == 0) {
            presenter.getRequest()
        }

        binding.rvLocations.adapter = adapter
        binding.rvLocations.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun seeResidents(residents: LocationResult) {
        val bundle = Bundle().apply {
            putString(ResidentsFragment.BUNDLE_KEY, Gson().toJson(residents))
        }
        findNavController().navigate(R.id.nav_residents, bundle)
    }

    override fun onSuccessRequest(response: List<LocationResult>) {
        locationList.addAll(response)
        adapter.notifyItemRangeChanged(0, locationList.size)
    }

    override fun onError() = Toast.makeText(requireContext(), getString(R.string.error, "Locations"), Toast.LENGTH_LONG).show()
    override fun Loading(isLoading: Boolean) = Utils().handleProgressbar(isVisible, progress)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}