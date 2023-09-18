package com.study.drawer.prensetation

import android.util.Log
import com.study.drawer.datasource.EpisodesRemote
import com.study.drawer.model.EpisodeResult
import com.study.drawer.util.Utils
import com.study.drawer.contracts.CharacterDetailContract

class CharacterDetailPresenter(val data: EpisodesRemote = EpisodesRemote()):CharacterDetailContract.Presenter {

    lateinit var view: CharacterDetailContract.View

    override fun getEpisodesByCharacter(urlEpisodes: List<String>) {
        view.Loading(true)
        val ids = Utils().getIdFromUrl(urlEpisodes)
        data.getEpisodeByCharacter(ids,this)

    }

    override fun onSuccessRequest(response: List<EpisodeResult>) = view.onSuccessRequest(response)
    override fun onError() = view.onError()
    override fun Loading(isLoading: Boolean) = view.Loading(isLoading)
}