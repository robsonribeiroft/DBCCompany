package com.rrdev.base_presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<ViewState<T>>.setSuccess(data: T){
    value = ViewState(status = ViewStateStatus.Success(data))
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T){
    postValue(ViewState(status = ViewStateStatus.Success(data)))
}

fun <T> MutableLiveData<ViewState<T>>.setError(error: Throwable?){
    value = ViewState(status = ViewStateStatus.Error(error))
}

fun <T> MutableLiveData<ViewState<T>>.postError(error: Throwable?){
    postValue(ViewState(status = ViewStateStatus.Error(error)))
}

fun <T> MutableLiveData<ViewState<T>>.setLoading(){
    value = ViewState(status = ViewStateStatus.Loading)
}

fun <T> MutableLiveData<ViewState<T>>.postLoading(){
    postValue(ViewState(status = ViewStateStatus.Loading))
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

fun <T> LiveData<ViewState<T>>.isLoading() = value.isLoading()

fun <T> LiveData<ViewState<T>>.isSuccess() = value.isSuccess()

fun <T> LiveData<ViewState<T>>.isError() = value.isError()

fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
        onLoading: () -> Unit = {}
) {
    observe(lifecycleOwner, Observer { it.stateHandler(onSuccess, onError, onLoading) })
}