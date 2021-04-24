package com.peter.ziska.demoapp.base.usecase

import kotlinx.coroutines.CoroutineScope

abstract class BaseParameterUseCase<in I, out O>() {
    abstract fun run(input: I): O

    operator fun invoke(input: I) = run(input)

    operator fun invoke(scope: CoroutineScope, input: I, response: (O) -> Unit) {
        response(run(input))
    }
}