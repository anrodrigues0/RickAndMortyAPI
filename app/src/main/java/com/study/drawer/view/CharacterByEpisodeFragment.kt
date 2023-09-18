package com.study.drawer.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.study.drawer.R
import com.study.drawer.databinding.FragmentCharactersByEpisodeBinding
import com.study.drawer.model.CharacterById
import com.study.drawer.model.EpisodeResult
import com.study.drawer.contracts.CharacterByEpisodeContract
import com.study.drawer.prensetation.CharacterByEpisodePresenter
import com.study.drawer.view.adapters.CharacterByEpisodeAdapter

class CharacterByEpisodeFragment : Fragment(R.layout.fragment_characters_by_episode), CharacterByEpisodeContract.View {

    private var _binding: FragmentCharactersByEpisodeBinding? = null;
    private val  binding get() = _binding!!

    private var presenter = CharacterByEpisodePresenter()
    private var characterByIdLst: MutableList<CharacterById> = ArrayList()

    lateinit var characterAdapter: CharacterByEpisodeAdapter

    companion object {
        const val BUNDLE_KEY = "episodeBundle"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersByEpisodeBinding.bind(view)

        val episode = Gson().fromJson(arguments?.getString(BUNDLE_KEY), EpisodeResult::class.java)
        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = episode.name;

        presenter.getCharactersByEpisode(episode.characters)

        characterAdapter = CharacterByEpisodeAdapter(characterByIdLst)
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacters.adapter = characterAdapter

        binding.txtName.text = episode.name
        binding.txtDate.text = episode.air_date
        binding.txtSeason.text = episode.episode
    }

    override fun showCharacters(characters: List<CharacterById>) {
        characterByIdLst.addAll(characters);
        characterAdapter.notifyItemInserted(characters.size);
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}