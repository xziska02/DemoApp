package com.peter.ziska.demoapp.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {

    open fun onCreate(arguments: Bundle?) {}
}