package com.peter.ziska.demoapp.flows.data.service

sealed class RestError {
    object Timeout : RestError()
    object InvalidResponse : RestError()
    class Error(val message:String): RestError()
}