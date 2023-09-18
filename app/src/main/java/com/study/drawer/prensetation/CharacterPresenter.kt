package com.study.drawer.prensetation

import android.util.Log
import com.study.drawer.contracts.BaseContract
import com.study.drawer.datasource.CharactersRemote
import com.study.drawer.model.Character

class CharacterPresenter(private val data: CharactersRemote = CharactersRemote()): BaseContract.Presenter<List<Character>> {

    lateinit var view: BaseContract.View<List<Character>>

    override fun getRequest() {
        view.Loading(true)
        data.getCharacters(this)
    }

    override fun setError() = view.onError()
    override fun Loading(isLoading: Boolean) = view.Loading(isLoading)
    override fun onSuccessRequest(response: List<Character>) = view.onSuccessRequest(response)
}