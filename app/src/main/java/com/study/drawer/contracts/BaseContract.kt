package com.study.drawer.contracts

interface BaseContract {
    interface View<T> {
        fun onSuccessRequest(response:T)
        fun onError()
        fun Loading(isLoading: Boolean)
    }

    interface  Presenter<T> {
        fun getRequest()
        fun setError()
        fun Loading(isLoading: Boolean)
        fun onSuccessRequest(response: T)
    }
}