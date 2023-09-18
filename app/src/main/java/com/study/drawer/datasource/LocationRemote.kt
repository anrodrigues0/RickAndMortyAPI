package com.study.drawer.datasource

import com.study.drawer.contracts.BaseContract
import com.study.drawer.model.LocationResult
import com.study.drawer.model.LocationRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationRemote {

    fun getLocation(callback: BaseContract.Presenter<List<LocationResult>>) {
        RetrofitService.getLocations().enqueue(object :Callback<LocationRoot> {
            override fun onResponse(call: Call<LocationRoot>, response: Response<LocationRoot>) {
                if(response.isSuccessful){
                    callback.onSuccessRequest( response.body()?.results ?: emptyList())
                }
                callback.Loading(false)
            }

            override fun onFailure(call: Call<LocationRoot>, t: Throwable) {
                callback.setError()
                callback.Loading(false)
            }
        })
    }
}