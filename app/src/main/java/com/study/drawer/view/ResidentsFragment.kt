package com.study.drawer.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.study.drawer.R
import com.study.drawer.databinding.FragmentResidentsBinding
import com.study.drawer.model.CharacterById
import com.study.drawer.model.LocationResult
import com.study.drawer.contracts.CharacterByEpisodeContract
import com.study.drawer.prensetation.CharacterByEpisodePresenter
import com.study.drawer.view.adapters.CharacterByEpisodeAdapter


class ResidentsFragment:Fragment(R.layout.fragment_residents), CharacterByEpisodeContract.View {

    val presenter = CharacterByEpisodePresenter()

    private var _binding: FragmentResidentsBinding? = null;
    private val binding get() = _binding!!

    lateinit var adapter: CharacterByEpisodeAdapter
    private val residentsList: MutableList<CharacterById> = ArrayList()

    companion object {
        const val BUNDLE_KEY = "residentsBundle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResidentsBinding.bind(view)

        val residents = Gson().fromJson(arguments?.getString(BUNDLE_KEY), LocationResult::class.java)
        presenter.getCharactersByEpisode(residents.residents)

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = "Residents ${residents.name}"

        adapter = CharacterByEpisodeAdapter(residentsList)

        binding.rvResidentsCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvResidentsCharacters.adapter = adapter;

    }

    override fun showCharacters(characters: List<CharacterById>) {
        residentsList.addAll(characters)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}