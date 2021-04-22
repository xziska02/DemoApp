package com.peter.ziska.demoapp.flows.view.news.navigation

import androidx.navigation.NavController
import com.peter.ziska.demoapp.flows.domain.model.Article
import com.peter.ziska.demoapp.flows.view.news.view.NewsFragmentDirections
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class NewsNavigator @Inject constructor() {
    lateinit var navController: NavController

    fun init(navController: NavController) {
        this.navController = navController
    }

    fun navigateToNewsDetail(article: Article) {
        val json = Json.encodeToString(article)
        navController.navigate(NewsFragmentDirections.actionSampleFragmentToNewsDetailFragment(json))
    }

}