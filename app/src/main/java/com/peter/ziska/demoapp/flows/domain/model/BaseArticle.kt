package com.peter.ziska.demoapp.flows.domain.model

import androidx.annotation.IntDef
import com.peter.ziska.demoapp.flows.domain.model.BaseArticle.ArticleType.Companion.BASIC_ARTICLE
import com.peter.ziska.demoapp.flows.domain.model.BaseArticle.ArticleType.Companion.GAME_ARTICLE
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseArticle(@ArticleType val type: Int) {
    abstract val id: String

    @IntDef(
        BASIC_ARTICLE,
        GAME_ARTICLE
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class ArticleType {

        companion object {
            const val BASIC_ARTICLE = 1000
            const val GAME_ARTICLE = 1001
        }
    }
}