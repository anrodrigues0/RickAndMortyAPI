package com.study.drawer.datasource

import com.study.drawer.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DatasourceApi {

    @GET("episode")
    fun getEpisodes():Call<Episodes>

    @GET("character/{ids}")
    fun getCharacterById(@Path("ids") ids:List<String>): Call<List<Character>>

    @GET("character")
    fun getCharacter():Call<CharacterRoot>

    @GET("episode/{ids}")
    fun getEpisodeByCharacter(@Path("ids") ids: List<String>): Call<List<EpisodeResult>>

    @GET("location")
    fun getLocations():Call<LocationRoot>
}