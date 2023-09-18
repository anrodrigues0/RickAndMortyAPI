package com.study.drawer.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.study.drawer.R
import com.study.drawer.contracts.CharacterDetailContract
import com.study.drawer.databinding.FragmentCharacterDetailBinding
import com.study.drawer.model.Character
import com.study.drawer.model.EpisodeResult
import com.study.drawer.prensetation.CharacterDetailPresenter
import com.study.drawer.view.adapters.EpisodesAdapters
import com.study.drawer.view.components.LoadingEpisodeItem
import java.lang.Exception

class CharacterDetailFragment:Fragment(R.layout.fragment_character_detail), CharacterDetailContract.View {

    private var _binding: FragmentCharacterDetailBinding? = null;
    private val binding get() = _binding!!

    val presenter = CharacterDetailPresenter()
    private val listEpisodesByCharacter: MutableList<EpisodeResult> = ArrayList()
    lateinit var adapter: EpisodesAdapters
    lateinit var loadingEpisodeItem: LoadingEpisodeItem

    companion object {
        const val bundleKey = "characterDetailBundle"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)
        presenter.view = this

        loadingEpisodeItem = binding.loadingEpisodeItem
        val character = Gson().fromJson(arguments?.getString(bundleKey), Character::class.java)

        presenter.getEpisodesByCharacter(character.episode)

        binding.txtNameCharacter.text = character.name
        binding.txtStatusCharacter.text = character.status
        binding.txtSpecieCharacter.text = character.species
        binding.txtOriginCharacter.text = character.origin.name
        binding.txtOriginCharacter.text = character.location.name


        Picasso.get()
            .load(character.image)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val drawableLayer:LayerDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.shadow_image) as LayerDrawable

                    val imgLayer = BitmapDrawable(resources, bitmap)
                    drawableLayer.setDrawableByLayerId(R.id.img_shadow, imgLayer)
                    binding.imgCharacterDetail.setImageDrawable(drawableLayer)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })

        adapter = EpisodesAdapters(listEpisodesByCharacter, true) {}
        binding.rvEpisodesCharacter.adapter = adapter
        binding.rvEpisodesCharacter.layoutManager =  LinearLayoutManager(requireContext())

    }

    override fun onSuccessRequest(response: List<EpisodeResult>) {
        listEpisodesByCharacter.addAll(response)
         adapter.notifyItemRangeChanged(0, listEpisodesByCharacter.size)
    }

    override fun onError() = Toast.makeText(requireContext(), getString(R.string.error, "Character Detail"), Toast.LENGTH_LONG).show()
    override fun Loading(isLoading: Boolean) =  loadingEpisodeItem.setVisible(isLoading)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}