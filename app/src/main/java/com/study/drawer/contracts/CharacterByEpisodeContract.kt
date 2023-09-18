package com.study.drawer.contracts

import com.study.drawer.model.CharacterById

interface CharacterByEpisodeContract {

    interface  View {
        fun showCharacters(characters: List<CharacterById>)
    }

    interface Presenter {
       fun getCharactersByEpisode(charactersUrl: List<String>)
       fun onSuccess(characters: List<CharacterById>)
    }
}