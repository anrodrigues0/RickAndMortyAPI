package com.study.drawer.datasource

import com.study.drawer.contracts.BaseContract
import com.study.drawer.model.Character
import com.study.drawer.model.CharacterById
import com.study.drawer.model.CharacterRoot
import com.study.drawer.contracts.CharacterByEpisodeContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersRemote {

    fun getCharactersByIds(ids: List<String>, callback: CharacterByEpisodeContract.Presenter) {
        RetrofitService.getCharactersByIds(ids).enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                if(response.isSuccessful){
                    val charactersByIdCasting = response.body()?.map { item ->
                        CharacterById(item.name, item.image)
                    }

                    callback.onSuccess(charactersByIdCasting ?: emptyList())
                }

            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {

            }

        })
    }

    fun getCharacters(callback:BaseContract.Presenter<List<Character>>) {
        RetrofitService.getCharacter().enqueue(object :Callback<CharacterRoot> {
            override fun onResponse(call: Call<CharacterRoot>, response: Response<CharacterRoot>) {
                if(response.isSuccessful){
                    callback.onSuccessRequest(response.body()?.results ?: emptyList())
                }
                callback.Loading(false)
            }

            override fun onFailure(call: Call<CharacterRoot>, t: Throwable) {
                callback.Loading(false)
                callback.setError()
            }
        })
    }
}