package com.peter.ziska.demoapp.flows.view.sample.view

import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.flows.view.sample.presenter.SampleViewModel

class SampleFragment : BaseFragment<SampleViewModel>(R.layout.sample_fragment) {

    override val viewModelClass: Class<SampleViewModel>
        get() = SampleViewModel::class.java

    override fun onInitializeView() {
        super.onInitializeView()
    }

}