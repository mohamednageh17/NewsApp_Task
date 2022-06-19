package com.example.movieapp.handle_state

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<StateData<T>?>() {

    fun postLoading() {
        value = StateData<T>().loading()
    }

    fun postSuccess(data: T) {
        value = StateData<T>().success(data)
    }

    fun postError(throwable: Throwable?) {
        value = StateData<T>().error(throwable)
    }

}