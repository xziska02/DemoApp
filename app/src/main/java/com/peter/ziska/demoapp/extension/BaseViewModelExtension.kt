package com.peter.ziska.demoapp.extension

import androidx.lifecycle.viewModelScope
import com.peter.ziska.demoapp.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun BaseViewModel.launchOnIO(run: suspend () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        run()
    }