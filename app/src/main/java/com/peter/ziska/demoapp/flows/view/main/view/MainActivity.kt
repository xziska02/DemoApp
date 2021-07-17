package com.peter.ziska.demoapp.flows.view.main.view

import android.os.Bundle
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.peter.ziska.demoapp.flows.view.main.presenter.MainViewModel
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main) {

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var inflater: NavInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        navHostFragment = navigation_host_fragment as NavHostFragment
        inflater = navHostFragment.navController.navInflater

        toolbar.setupWithNavController(navHostFragment.navController, null)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance)
        toolbar.isTitleCentered = true
    }
}