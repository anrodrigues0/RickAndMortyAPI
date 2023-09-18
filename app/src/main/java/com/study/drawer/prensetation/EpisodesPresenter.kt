package com.study.drawer.prensetation

import androidx.annotation.StringRes
import com.study.drawer.contracts.BaseContract
import com.study.drawer.datasource.EpisodesRemote
import com.study.drawer.model.EpisodeResult
import com.study.drawer.model.Episodes

class EpisodesPresenter(private val data: EpisodesRemote = EpisodesRemote()): BaseContract.Presenter<Episodes> {

    lateinit var view: BaseContract.View<List<EpisodeResult>>

    override fun getRequest() {
        view.Loading(true)
        data.getEpisodes(this)
    }

    override fun setError() = view.onError()
    override fun Loading(isLoading: Boolean) = view.Loading(isLoading)
    override fun onSuccessRequest(response: Episodes) = view.onSuccessRequest(response.results)
}