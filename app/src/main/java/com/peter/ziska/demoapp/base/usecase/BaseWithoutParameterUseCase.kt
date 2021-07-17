package com.peter.ziska.demoapp.base.usecase

import kotlinx.coroutines.*

abstract class BaseWithoutParameterUseCase<out O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private lateinit var job: Job

    abstract fun run(): O

    operator fun invoke() = run()

    operator fun invoke(scope: CoroutineScope, response: (O) -> Unit) {
        job = scope.launch {
            response(withContext(dispatcher) { run() })
        }
    }

    fun cancel() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}