package com.peter.ziska.demoapp.flows.data.service

sealed class RestResult {
    object Timeout : RestResult()
    object InvalidResponse : RestResult()
    class Error(val message:String): RestResult()
}