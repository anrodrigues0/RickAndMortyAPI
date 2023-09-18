package com.study.drawer.datasource

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun httpClient():OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun retrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient())
            .build()
    }



    fun getEpisode() = retrofit().create(DatasourceApi::class.java).getEpisodes()
    fun getCharactersByIds(ids:List<String>) = retrofit().create(DatasourceApi::class.java).getCharacterById(ids)
    fun getCharacter() = retrofit().create(DatasourceApi::class.java).getCharacter()
    fun getEpisodeByCharacter(ids:List<String>) = retrofit().create(DatasourceApi::class.java).getEpisodeByCharacter(ids)
    fun getLocations() = retrofit().create(DatasourceApi::class.java).getLocations()
}