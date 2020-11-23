package com.rrdev.base_presentation

import androidx.lifecycle.MutableLiveData

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}