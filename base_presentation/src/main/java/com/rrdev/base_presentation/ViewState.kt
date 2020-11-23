package com.rrdev.base_presentation

sealed class ViewStateStatus{
    class Success<T> (val data: T? = null): ViewStateStatus(){ companion object }
    class Error(val error: Throwable? = null): ViewStateStatus(){ companion object }
    object Loading: ViewStateStatus()
    object Neutral: ViewStateStatus()
}

class ViewState<T>(
        val status: ViewStateStatus = ViewStateStatus.Neutral
){

    @Suppress("UNCHECKED_CAST")
    fun stateHandler(
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit,
            loading: () -> Unit
    ) {
        when (status) {
            is ViewStateStatus.Success<*> -> {
                status.data?.let { onSuccess(it as T) } ?: throw RuntimeException()
            }
            is ViewStateStatus.Error -> status.error?.let { onError(it) } ?: throw RuntimeException()
            is ViewStateStatus.Loading -> loading()
            else -> Unit
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun asData(): T {
        if (this is ViewStateStatus.Success<*>)
            return this.data as T
        throw Exception("ViewStateStatus is not success")
    }
}

fun <T> ViewState<T>?.isSuccess() = this?.status?.equals(ViewStateStatus.Success) ?: false
fun <T> ViewState<T>?.isError() = this?.status?.equals(ViewStateStatus.Error) ?: false
fun <T> ViewState<T>?.isLoading() = this?.status?.equals(ViewStateStatus.Loading) ?: false
