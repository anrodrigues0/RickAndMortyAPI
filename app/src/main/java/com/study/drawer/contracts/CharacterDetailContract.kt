package com.study.drawer.contracts

import com.study.drawer.model.EpisodeResult

interface CharacterDetailContract {

    interface View: BaseContract.View<List<EpisodeResult>>

    interface Presenter: BaseContract.View<List<EpisodeResult>> {
        fun getEpisodesByCharacter(urlEpisodes: List<String>)
    }
}