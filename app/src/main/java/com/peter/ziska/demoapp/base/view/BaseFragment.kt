package com.peter.ziska.demoapp.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.ziska.demoapp.base.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment<VM : ViewModel>(@LayoutRes val layoutResId: Int) : Fragment() {

    protected abstract val viewModelClass: Class<VM>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _viewModel: VM? = null
    val viewModel: VM
        get() = _viewModel ?: throw IllegalArgumentException("Fragment viewmodel not ready!!!")

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResId, container, false)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        super.onViewCreated(view, savedInstanceState)
        onInitializeView()
        onSubscribe()
    }

    protected open fun onSubscribe() {}

    protected open fun onInitializeView() {}

}