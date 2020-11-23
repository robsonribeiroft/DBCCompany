package com.rrdev.domain.usecase

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class UseCase<T, in Params> {

    abstract fun run(params: Params? = null): Flow<T>

    operator fun invoke(
        params: Params? = null,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        scope: CoroutineScope = GlobalScope,
        runContext: CoroutineDispatcher = Dispatchers.IO,
        watchContext: CoroutineDispatcher = Dispatchers.Main
    ){
        scope.launch(runContext) {
            try {
                run(params).collect {
                    withContext(watchContext) {
                        onSuccess(it)
                    }
                }
            } catch (e: Exception) {
                withContext(watchContext) {
                    onError(e)
                }
            }
        }
    }

    fun cancel(scope: CoroutineScope) = scope.coroutineContext.cancelChildren()

}