package com.peter.ziska.demoapp.base.usecase

import kotlinx.coroutines.*

abstract class BaseParameterUseCase<in I, out O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private lateinit var job: Job

    abstract suspend fun run(input: I): O

    suspend operator fun invoke(input: I) = run(input)

    operator fun invoke(scope: CoroutineScope, input: I, response: (O) -> Unit) {
        job = scope.launch {
            response(withContext(dispatcher) { run(input) })
        }
    }

    fun cancel() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}