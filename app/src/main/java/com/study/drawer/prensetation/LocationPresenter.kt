package com.study.drawer.prensetation

import com.study.drawer.contracts.BaseContract
import com.study.drawer.datasource.LocationRemote
import com.study.drawer.model.LocationResult

class LocationPresenter(private var data: LocationRemote = LocationRemote() ): BaseContract.Presenter<List<LocationResult>> {

    lateinit var view:BaseContract.View<List<LocationResult>>

    override fun getRequest() {
        view.Loading(true)
        data.getLocation(this)
    }

    override fun setError() = view.onError()
    override fun Loading(isLoading: Boolean) = view.Loading(isLoading)
    override fun onSuccessRequest(response: List<LocationResult>) = view.onSuccessRequest(response)

}