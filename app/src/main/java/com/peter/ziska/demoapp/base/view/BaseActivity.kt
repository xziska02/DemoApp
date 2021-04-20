package com.peter.ziska.demoapp.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.ziska.demoapp.base.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel>(@LayoutRes val layoutResId: Int) : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected abstract val viewModelClass: Class<VM>

    private var _viewModel: VM? = null
    val viewModel: VM
        get() = _viewModel ?: throw IllegalArgumentException("Viewmodel not ready")

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        _viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        onInitializeView()
        onSubscribe()
    }

    protected open fun onSubscribe() {}

    protected open fun onInitializeView() {}
}