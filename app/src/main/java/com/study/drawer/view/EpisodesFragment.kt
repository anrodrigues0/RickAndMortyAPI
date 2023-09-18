package com.study.drawer.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.study.drawer.R
import com.study.drawer.contracts.BaseContract
import com.study.drawer.databinding.FragmentEpisodesBinding
import com.study.drawer.model.EpisodeResult
import com.study.drawer.prensetation.EpisodesPresenter
import com.study.drawer.util.Utils
import com.study.drawer.view.adapters.EpisodesAdapters
import com.study.drawer.view.components.Error

class EpisodesFragment: Fragment(R.layout.fragment_episodes), BaseContract.View<List<EpisodeResult>> {

    private var _binding:FragmentEpisodesBinding? = null;
    private val binding get() = _binding!!;

    var presenter = EpisodesPresenter();
    private lateinit var errorLayout: Error
    private lateinit var adapterEpisodes: EpisodesAdapters

    private val episodeList: MutableList<EpisodeResult> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodesBinding.bind(view)
        presenter.view = this;

        errorLayout = binding.episodeError
        adapterEpisodes = EpisodesAdapters(episodeList) {
            onPressItem(it)
        }

        if (adapterEpisodes.itemCount == 0) {
            presenter.getRequest()
        }

        binding.rvEpisodes.adapter = adapterEpisodes
        binding.rvEpisodes.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun onPressItem(episode: EpisodeResult) {
        val gson = Gson();
        val episodeJson = gson.toJson(episode)

        val bundle = Bundle().apply {
            putString(CharacterByEpisodeFragment.BUNDLE_KEY, episodeJson)
        }

        findNavController().navigate(R.id.nav_characters_by_episode, bundle)
    }


    override fun onSuccessRequest(response: List<EpisodeResult>) {
        episodeList.addAll(response)
        adapterEpisodes.notifyItemInserted(episodeList.size)
    }

    override fun onError()  {
        errorLayout.setVisibility(true)
        errorLayout.setMsgError(getString(R.string.error, "Episode"))
    }

    override fun Loading(isLoading: Boolean)  = Utils().handleProgressbar(isLoading, binding.progress)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}