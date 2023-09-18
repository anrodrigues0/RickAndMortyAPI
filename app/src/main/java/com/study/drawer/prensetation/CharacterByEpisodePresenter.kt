package com.study.drawer.prensetation
import com.study.drawer.contracts.CharacterByEpisodeContract
import com.study.drawer.datasource.CharactersRemote
import com.study.drawer.model.CharacterById
import com.study.drawer.util.Utils

class CharacterByEpisodePresenter(private val data: CharactersRemote = CharactersRemote()): CharacterByEpisodeContract.Presenter {

    lateinit var view: CharacterByEpisodeContract.View

    override fun getCharactersByEpisode(charactersUrl: List<String>) {
       val idsCharacters = Utils().getIdFromUrl(charactersUrl)
        data.getCharactersByIds(idsCharacters, this)
    }

    override fun onSuccess(characters: List<CharacterById>) {
        view.showCharacters(characters)
    }
}