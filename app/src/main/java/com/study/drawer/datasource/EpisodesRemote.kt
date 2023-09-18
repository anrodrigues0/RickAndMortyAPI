package com.study.drawer.datasource

import android.provider.Settings.Global.getString
import com.study.drawer.R
import com.study.drawer.contracts.BaseContract
import com.study.drawer.model.EpisodeResult
import com.study.drawer.model.Episodes
import com.study.drawer.contracts.CharacterDetailContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesRemote {

    fun getEpisodes(callback: BaseContract.Presenter<Episodes>) {
            RetrofitService.getEpisode().enqueue(object : Callback<Episodes> {
                override fun onResponse(call: Call<Episodes>, response: Response<Episodes>) {
                    if (response.isSuccessful) {
                        val episodes = response.body()
                        if (episodes != null) {
                            callback.onSuccessRequest(episodes)
                        }
                    }

                    callback.Loading(false)
                }

                override fun onFailure(call: Call<Episodes>, t: Throwable) {
                      callback.Loading(false)
                      callback.setError()
                }
            })
    }

    fun getEpisodeByCharacter(ids:List<String>, callback: CharacterDetailContract.Presenter) {
            RetrofitService.getEpisodeByCharacter(ids).enqueue(object :Callback<List<EpisodeResult>> {
                override fun onResponse(
                    call: Call<List<EpisodeResult>>,
                    response: Response<List<EpisodeResult>>
                ) {
                    if (response.isSuccessful) {
                        val episodes = response.body()
                        callback.onSuccessRequest(episodes ?: emptyList())
                    }

                    callback.Loading(false)
                }

                override fun onFailure(call: Call<List<EpisodeResult>>, t: Throwable) {
                    callback.onError()
                    callback.Loading(false)
                }
            })
    }

}