package com.study.drawer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.study.drawer.R
import com.study.drawer.contracts.BaseContract
import com.study.drawer.databinding.FragmentCharctersBinding
import com.study.drawer.model.Character
import com.study.drawer.prensetation.CharacterPresenter
import com.study.drawer.util.Utils
import com.study.drawer.view.adapters.CharacterAdapter
import com.study.drawer.view.adapters.CharacterByEpisodeAdapter
import com.study.drawer.view.components.Error

class CharactersFragment : Fragment(R.layout.fragment_charcters), BaseContract.View<List<Character>> {

    private var _binding: FragmentCharctersBinding? = null;
    private val binding get() = _binding!!

    lateinit var errorLayout: Error

    val presenter = CharacterPresenter()
    private val charactersList: MutableList<Character> = ArrayList()
    lateinit var adapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this

        _binding = FragmentCharctersBinding.bind(view)

        errorLayout = binding.errorCharacters
        adapter = CharacterAdapter(charactersList) { onSelectCharacter(it) }

        if (adapter.itemCount == 0) {
            presenter.getRequest()
        }

        binding.rvCharactersAll.adapter = adapter
        binding.rvCharactersAll.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun onSelectCharacter(character: Character) {
        val gson = Gson()
        val characterDetail = gson.toJson(character)
        val bundle = Bundle().apply {
            putString(CharacterDetailFragment.bundleKey, characterDetail)
        }

        findNavController().navigate(R.id.nav_character_detail, bundle)
    }

    override fun onSuccessRequest(response: List<Character>) {
        charactersList.addAll(response)
        adapter.notifyItemRangeChanged(0, response.size)
    }

    override fun onError() {
        errorLayout.setVisibility(true)
        errorLayout.setMsgError(getString(R.string.error, "Characters"))
    }
    override fun Loading(isLoading: Boolean) = Utils().handleProgressbar(isLoading, binding.progressCharacter)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}